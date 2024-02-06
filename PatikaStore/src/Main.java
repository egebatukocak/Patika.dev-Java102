public class Main {
    public static void main(String[] args) {

        CategoryController categoryController = new CategoryController();
        BrandController brandController = new BrandController();
        ProductController productController = new ProductController(categoryController, brandController);

        PatikaStore patikaStore = new PatikaStore(categoryController, brandController, productController);

        patikaStore.menu();

    }
}
