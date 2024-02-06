import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private static List<Products> products = new ArrayList<>();
    private static int lastProductId;
    private CategoryController categoryController;
    private BrandController brandController;

    public ProductController(CategoryController categoryController, BrandController brandController) {
        this.categoryController = categoryController;
        this.brandController = brandController;
        createDefaultObject();
    }

    private void createDefaultObject(){
        Category cellphone = categoryController.getCategoryByName("Cep Telefonu");
        Category notebook = categoryController.getCategoryByName("Notebook");

        // Default Cellphones
        products.add(
                new Products(++lastProductId, "SAMSUNG GALAXY A51", cellphone,
                        3199, 0,10,brandController.getBrandByName("Samsung"),
                        "128 GB","6.5 inc","6 GB","Siyah", "4000 mAh")
        );
        products.add(
                new Products(++lastProductId, "iPhone 11", cellphone,
                        7379, 0,5,brandController.getBrandByName("Apple"),
                        "64 GB","6.1 inc","6 GB","Blue", "3046 mAh")
        );

        products.add(
                new Products(++lastProductId, "Red-Mi Note 10 Pro", cellphone,
                        4012, 0,15,brandController.getBrandByName("Xiaomi"),
                        "128 GB","6.5 inc","12 GB","White", "4000 mAh")
        );

        // Default notebooks
        products.add(
                new Products(++lastProductId, "HUAWEI Matebook 14", notebook,
                        7000, 0,4,brandController.getBrandByName("Huawei"),
                        "512 GB","14 inc","16 GB", "---", "---")
        );

        products.add(
                new Products(++lastProductId, "LENOVO V14 IGL", notebook,
                        3699, 0,4,brandController.getBrandByName("Lenovo"),
                        "1 TB","14 inc","8 GB", "---", "---")
        );

        products.add(
                new Products(++lastProductId, "ASUS Tuf Gaming", notebook,
                        8199, 0,4,brandController.getBrandByName("Asus"),
                        "2 TB","15.6 inc","2x16 GB", "---", "---")
        );
    }

    public List<Products> getProducts() {
        return products;
    }

    public void deleteUncategorizedProducts(){
        for (Products p : products){
            if (p.getProductCategory() == null){
                products.remove(p);
                deleteUncategorizedProducts();
                break;
            }
        }
    }

    public void deleteUnbrandedProducts(){
        for (Products p : products){
            if(p.getProductBrand() == null){
                products.remove(p);
                deleteUnbrandedProducts();
                break;
            }
        }
    }

    public int getLastProductId() {
        return lastProductId;
    }

    public void addNewProduct(String productName, Category productCategory, double price, double discountRate,
                              int stock, Brand productBrand, String internalStorage, String screenSize,
                              String ramCapacity, String color, String batteryCapacity){
        products.add( new Products(++lastProductId,productName,productCategory,price,discountRate,
                stock,productBrand,internalStorage,screenSize,
                ramCapacity,color,batteryCapacity));
        System.out.println("Ürün Eklendi.");
    }
    public void deleteProduct(Products product){
        products.remove(product);
    }
}
