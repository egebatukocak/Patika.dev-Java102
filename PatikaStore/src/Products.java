public class Products {
    private int productId;
    private String productName;
    private Brand productBrand;
    private Category productCategory;
    private double price;
    private double discountRate;
    private int stock;
    private String internalStorage;
    private String screenSize;
    private String ramCapacity;
    private String color;
    private String batteryCapacity;

    public Products(int productId, String productName, Category productCategory, double price, double discountRate,
                    int stock, Brand productBrand, String internalStorage, String screenSize,
                    String ramCapacity, String color, String batteryCapacity) {
        this.productId = productId;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productCategory = productCategory;
        this.price = price;
        this.discountRate = discountRate;
        this.stock = stock;
        this.internalStorage = internalStorage;
        this.screenSize = screenSize;
        this.ramCapacity = ramCapacity;
        this.color = color;
        this.batteryCapacity = batteryCapacity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Brand getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(Brand productBrand) {
        this.productBrand = productBrand;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getInternalStorage() {
        return internalStorage;
    }

    public void setInternalStorage(String internalStorage) {
        this.internalStorage = internalStorage;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getRamCapacity() {
        return ramCapacity;
    }

    public void setRamCapacity(String ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
