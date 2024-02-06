import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PatikaStore {
    private static Scanner scan = new Scanner(System.in);
    private CategoryController categoryController;
    private BrandController brandController;
    private ProductController productController;

    public PatikaStore(CategoryController categoryController, BrandController brandController, ProductController productController) {
        this.categoryController = categoryController;
        this.brandController = brandController;
        this.productController = productController;
    }

    public void menu(){
        String str ="PatikaStore Ürün Yönetim Paneli !\n" +
                "1 - Kategori İşlemleri\n" +
                "2 - Marka İşlemleri\n" +
                "3 - Ürün İşlemleri\n" +
                "0 - Çıkış Yap";
        System.out.println(str);

        int preference = getIntegerFromMinToMaxFromUser(0,3,"Tercihiniz : ");

        switch (preference){
            case 0:
                exit();
                break;
            case 1:
                categoryOperationsMenu();
                break;
            case 2:
                brandOperationsMenu();
                break;
            case 3:
                productOperationsMenu();
        }
    }

    private void categoryOperationsMenu(){
        String str =
                "------------------------------\n" +
                        "Patika Store Kategori İşlemleri!\n" +
                        "1- Kategorileri Listele\n" +
                        "2- Kategoriye göre Ürünleri Listele\n" +
                        "3- Yeni Kategori Ekle\n" +
                        "4- Kategori Sil\n" +
                        "0- Ana Menüye Dön\n" +
                        "------------------------------";
        System.out.println(str);

        int preference = getIntegerFromMinToMaxFromUser(0,4,"Tercihiniz : ");

        switch (preference){
            case 0:
                menu();
                break;
            case 1:
                listAllCategories();
                break;
            case 2:
                listProductsByCategory();
                break;
            case 3:
                addNewCategory();
                break;
            case 4:
                deleteCategory();
                break;
        }
    }

    private void brandOperationsMenu(){
        String str =
                "------------------------------\n" +
                        "Patika Store Marka İşlemleri!\n" +
                        "1- Markaları Listele\n" +
                        "2- Markaya göre Ürünleri Listele\n" +
                        "3- Yeni Marka Ekle\n" +
                        "4- Marka Sil\n" +
                        "0- Ana Menüye Dön\n" +
                        "------------------------------";
        System.out.println(str);

        int preference = getIntegerFromMinToMaxFromUser(0,4,"Tercihiniz : ");

        switch (preference){
            case 0:
                menu();
                break;
            case 1:
                listAllBrands();
                break;
            case 2:
                listProductsByBrand();
                break;
            case 3:
                addNewBrand();
                break;
            case 4:
                deleteBrand();
                break;
        }
    }

    private void productOperationsMenu(){
        String str =
                "------------------------------\n" +
                        "Patika Store Ürün İşlemleri!\n" +
                        "1- Ürünleri Listele\n" +
                        "2- Yeni Ürün Ekle\n" +
                        "3- Ürün Sil\n" +
                        "0- Ana Menüye Dön\n" +
                        "------------------------------";
        System.out.println(str);

        int preference = getIntegerFromMinToMaxFromUser(0,4,"Tercihiniz : ");

        switch (preference){
            case 0:
                menu();
                break;
            case 1:
                listAllProducts();
                break;
            case 2:
                addNewProduct();
                break;
            case 3:
                deleteProduct();
                break;
        }
    }

    private void listAllCategories(){
        List<Category> categories = categoryController.getCategories();

        if(categories.size()==0){
            System.out.println("Kategori Listesi Boştur.");
            categoryOperationsMenu();
            return;
        }

        System.out.println("----------------------------\nKategoriler\n----------------------------");

        System.out.printf("| %1$-4s| %2$-20s|%n","ID","KATEGORİ ADI");

        for (Category c : categories){
            System.out.printf("| %1$-4d| %2$-20s|%n",c.getCategoryId(),c.getCategoryName());
        }

        System.out.println("----------------------------");

        categoryOperationsMenu();
    }

    private void listProductsByCategory(){
        List<Category> categories = categoryController.getCategories();

        if(categories.size() == 0){
            System.out.println("Kategori Listesi Boştur!");
            categoryOperationsMenu();
            return;
        }

        System.out.println("----------------------------\nKategori\n----------------------------");

        System.out.printf("| %1$-4s| %2$-20s|%n","ID","KATEGORİ ADI");

        for (Category c : categories){
            System.out.printf("| %1$-4d| %2$-20s|%n",c.getCategoryId(), c.getCategoryName());
        }

        System.out.println("----------------------------");

        Category category = null;
        while (category == null){
            int preference = getIntegerFromMinToMaxFromUser(0, categoryController.getLastCategoryId(),"Listelemek İstediğiniz Ürünlere Ulaşmak İçin İd'ye Göre Kategori Seçiniz : " );

            for (Category c : categories){
                if(c.getCategoryId() == preference) {
                    category = c;
                    break;
                }
            }
        }

        List<Products> products = productController.getProducts();

        System.out.println("----------------------------------------------\nÜrünler\n----------------------------------------------");

        System.out.printf("| %1$-4s| %2$-20s| %3$-15s|%n","ID","ÜRÜN ADI", "KATEGORİ");

        for (Products p : products){
            if(p.getProductCategory().equals(category))
                System.out.printf("| %1$-4d| %2$-20s| %3$-15s| %n",p.getProductId(),p.getProductName(), p.getProductCategory().getCategoryName());
        }

        System.out.println("----------------------------------------------");

        categoryOperationsMenu();
    }

    private void addNewCategory(){
        scan.nextLine();
        System.out.println("Yeni Kategorinin Adını Giriniz : ");
        String name = scan.nextLine();

        if(categoryController.getCategoryByName(name)!=null){
            System.out.println("Kategori Adı ("+name+") Zaten Mevcut.");
        }else{
            categoryController.addCategory(name);
            System.out.println(name+" Kategorisi Eklendi.");
        }
        categoryOperationsMenu();
    }

    private void deleteCategory(){
        List<Category> categoryList = categoryController.getCategories();

        if(categoryList.size()==0){
            System.out.println("Kategori Listesi Boştur!");
            categoryOperationsMenu();
            return;
        }

        System.out.println("----------------------------\nKategoriler\n----------------------------");

        System.out.printf("| %1$-4s| %2$-20s|%n","ID","KATEGORİ ADI");

        for (Category c : categoryList){
            System.out.printf("| %1$-4d| %2$-20s|%n",c.getCategoryId(), c.getCategoryName());
        }

        System.out.println("----------------------------");

        Category category = null;
        while (category == null){
            int preference = getIntegerFromMinToMaxFromUser(0,categoryController.getLastCategoryId(),"Silinmesini İstediğiniz Kategorinin İd'sini Seçiniz : ");

            for (Category c: categoryList){
                if(c.getCategoryId() == preference){
                    category=c;
                    break;
                }
            }
        }
        String repeatingMessage = "Kategori (" + category.getCategoryName() + ") ve \"Bağlantılı Tüm Ürünler\" Silinecektir. Onaylıyor Musunuz? (1-Evet, 2-Hayır) : ";
        int selected = getIntegerFromMinToMaxFromUser(1,2, repeatingMessage);

        if(selected == 1){

            for (Products p : productController.getProducts()){
                if(p.getProductCategory().equals(category)){
                    p.setProductCategory(null);
                }
            }
            categoryController.deleteCategory(category);

            System.out.println("Kategori Silindi.");

            productController.deleteUncategorizedProducts();
        }
        else {
            System.out.println("Silme İşlemi İptal Edildi...");
        }
        categoryOperationsMenu();
    }

    private void listAllBrands(){
        List<Brand> brands = brandController.getBrands();

        if(brands.size() == 0){
            System.out.println("Marka Listesi Boştur!");
            brandOperationsMenu();
            return;
        }
        System.out.println("----------------------------\nMarkalar\n----------------------------");

        System.out.printf("| %1$-4s| %2$-20s|%n","ID","BRAND NAME");

        for (Brand b : brands){
            System.out.printf("| %1$-4d| %2$-20s|%n",b.getBrandId(), b.getBrandName());
        }

        System.out.println("----------------------------");

        brandOperationsMenu();
    }

    private void listProductsByBrand(){
        List<Brand> brands = brandController.getBrands();

        if(brands.size() == 0){
            System.out.println("Marka Listesi Boştur!");
            brandOperationsMenu();
            return;
        }

        System.out.println("----------------------------\nMarkalar\n----------------------------");

        System.out.printf("| %1$-4s| %2$-20s|%n","ID","MARKA ADI");

        for (Brand b : brands){
            System.out.printf("| %1$-4d| %2$-20s|%n",b.getBrandId(), b.getBrandName());
        }

        System.out.println("----------------------------");

        Brand brand = null;
        while (brand == null){
            int preference = getIntegerFromMinToMaxFromUser(0, brandController.getLastBrandId(),"Listelemek İstediğiniz Ürünlere Ulaşmak İçin İd'ye Göre Marka Seçiniz : " );

            for (Brand b : brands){
                if(b.getBrandId() == preference) {
                    brand = b;
                    break;
                }
            }
        }

        List<Products> products = productController.getProducts();

        System.out.println("----------------------------------------------\nÜrünler\n----------------------------------------------");

        System.out.printf("| %1$-4s| %2$-20s| %3$-15s|%n","ID","ÜRÜN ADI", "KATEGORİ");

        for (Products p : products){
            if(p.getProductBrand().equals(brand))
                System.out.printf("| %1$-4d| %2$-20s| %3$-15s| %n",p.getProductId(),p.getProductName(), p.getProductCategory().getCategoryName());
        }

        System.out.println("----------------------------------------------");

        brandOperationsMenu();
    }

    private void addNewBrand(){
        scan.nextLine();
        System.out.println("Yeni Markanın Adını Giriniz : ");
        String name = scan.nextLine();

        if(brandController.getBrandByName(name)!=null){
            System.out.println("Marka Adı ("+name+") Zaten Mevcut.");
        }else{
            brandController.addBrand(name);
            System.out.println(name+" Markası Eklendi.");
        }
        brandOperationsMenu();
    }

    private void deleteBrand(){
        List<Brand> brandList = brandController.getBrands();

        if(brandList.size()==0){
            System.out.println("Marka Listesi Boştur!");
            brandOperationsMenu();
            return;
        }

        System.out.println("----------------------------\nMarkalar\n----------------------------");

        System.out.printf("| %1$-4s| %2$-20s|%n","ID","MARKA ADI");

        for (Brand b : brandList){
            System.out.printf("| %1$-4d| %2$-20s|%n",b.getBrandId(), b.getBrandName());
        }

        System.out.println("----------------------------");

        Brand brand = null;
        while (brand == null){
            int preference = getIntegerFromMinToMaxFromUser(0,brandController.getLastBrandId(),"Silinmesini İstediğiniz Markanın İd'sini Seçiniz : ");

            for (Brand b: brandList){
                if(b.getBrandId() == preference){
                    brand=b;
                    break;
                }
            }
        }
        String repeatingMessage = "Marka (" + brand.getBrandName() + ") ve \"Bağlantılı Tüm Ürünler\" Silinecektir. Onaylıyor Musunuz? (1-Evet, 2-Hayır) : ";
        int selected = getIntegerFromMinToMaxFromUser(1,2, repeatingMessage);

        if(selected == 1){

            for (Products p : productController.getProducts()){
                if(p.getProductBrand().equals(brand)){
                    p.setProductBrand(null);
                }
            }
            brandController.deleteBrand(brand);

            System.out.println("Marka Silindi.");

            productController.deleteUnbrandedProducts();
        }
        else {
            System.out.println("Silme İşlemi İptal Edildi...");
        }
        brandOperationsMenu();
    }

    private void listAllProducts(){
        List<Products> products = productController.getProducts();

        System.out.println("----------------------------\nÜrünler\n----------------------------");

        System.out.printf("| %1$-4s| %2$-20s|%n","ID","ÜRÜN ADI");

        for (Products p : products){
            System.out.printf("| %1$-4d| %2$-20s|%n",p.getProductId(),p.getProductName());
        }

        System.out.println("----------------------------");

        productOperationsMenu();
    }

    private void addNewProduct(){
        scan.nextLine();
        double price,discount;
        int stock;

        System.out.println("Lütfen İstenen Değerleri Giriniz.");

        System.out.println("Ürünün Adı : ");
        String name = scan.nextLine().trim();

        System.out.println("Markanın Adı : ");
        String brandName = scan.nextLine().trim();

        System.out.println("Kategorinin Adı : ");
        String categoryName = scan.nextLine().trim();

        System.out.println("Ekran Boyutu : ");
        String screenSize = scan.nextLine().trim();

        System.out.println("RAM : ");
        String ramCapacity = scan.nextLine().trim();

        System.out.println("Dahili Depolama : ");
        String internalStorage = scan.nextLine().trim();

        System.out.println("Rengi : ");
        String color = scan.nextLine().trim();

        System.out.println("Batarya : ");
        String batteryCapacity = scan.nextLine().trim();

        price = getDoubleFromMinToMaxFromUser(0,Double.MAX_VALUE,"Fiyatı : ");

        discount = getDoubleFromMinToMaxFromUser(0,Double.MAX_VALUE,"İndirimi (0-100) : ");

        stock = getIntegerFromMinToMaxFromUser(0,Integer.MAX_VALUE,"Stok Miktarı : ");

        Brand brand = brandController.getBrandByName(brandName);
        if(brand==null){
            brand = brandController.addAndGetBrand(brandName);
        }

        Category category = categoryController.getCategoryByName(categoryName);
        if (category == null) {
            category = categoryController.addAndGetCategory(categoryName);
        }

        productController.addNewProduct(name,category,price,discount,stock,brand,internalStorage,
                screenSize,ramCapacity,color,batteryCapacity);
        productOperationsMenu();
    }

    private void deleteProduct(){
        List<Products> productsList = productController.getProducts();

        if(productsList.size()==0){
            System.out.println("Ürün Listesi Boştur!");
            productOperationsMenu();
            return;
        }

        System.out.println("----------------------------\nÜrünler\n----------------------------");

        System.out.printf("| %1$-4s| %2$-20s|%n","ID","ÜRÜN ADI");

        for (Products p : productsList){
            System.out.printf("| %1$-4d| %2$-20s|%n",p.getProductId(), p.getProductName());
        }

        System.out.println("----------------------------");

        Products product = null;
        while (product == null){
            int preference = getIntegerFromMinToMaxFromUser(0,productController.getLastProductId(),"Silinmesini İstediğiniz Ürünün İd'sini Seçiniz : ");

            for (Products p: productsList){
                if(p.getProductId() == preference){
                    product=p;
                    break;
                }
            }
        }
        String repeatingMessage = "Ürün (" + product.getProductName() + ") Silinecektir. Onaylıyor Musunuz? (1-Evet, 2-Hayır) : ";
        int selected = getIntegerFromMinToMaxFromUser(1,2, repeatingMessage);

        if(selected == 1){

            productController.deleteProduct(product);

            System.out.println("Ürün Silindi.");
        }
        else {
            System.out.println("Silme İşlemi İptal Edildi...");
        }
        productOperationsMenu();
    }

    private void exit(){
        System.out.println("Çıkış Yapılıyor...");
    }

    private int getIntegerFromMinToMaxFromUser(int min,int max,String repeatingMessage){
        int selection;
        while(true){
            System.out.println(repeatingMessage);
            try{
                selection = scan.nextInt();
                if (selection>=min && selection<=max){
                    break;
                }
                else System.out.println("Yanlış Değer Girdiniz!");
            }catch (InputMismatchException e){
                System.out.println("Yanlış Değer Girdiniz!");
                scan.next();
            }
        }
        return selection;
    }

    private double getDoubleFromMinToMaxFromUser(double min,double max,String repeatingMessage){
        double selection;
        while(true){
            System.out.println(repeatingMessage);
            try{
                selection = scan.nextInt();
                if (selection>=min && selection<=max){
                    break;
                }
                else System.out.println("Yanlış Değer Girdiniz!");
            }catch (InputMismatchException e){
                System.out.println("Yanlış Değer Girdiniz!");
                scan.next();
            }
        }
        return selection;
    }
}
