import java.util.ArrayList;
import java.util.List;

public class BrandController {
    private static List<Brand> brands = new ArrayList<>();
    private static int lastBrandId;

    public BrandController() {
        createDefaultObjects();
    }

    private void createDefaultObjects() {
        String[] brandNames = {"Samsung", "Lenovo", "Apple", "Huawei", "Casper", "Asus", "HP", "Xiaomi", "Monster"};
        for (String name : brandNames) {
            brands.add(new Brand(++lastBrandId, name));
        }
    }

    public Brand getBrandByName(String name) {
        for (Brand brand : brands) {
            if (brand.getBrandName().equalsIgnoreCase(name)) return brand;
        }
        return null;
    }

    public static List<Brand> getBrands() {
        return brands;
    }

    public static int getLastBrandId() {
        return lastBrandId;
    }

    public void addBrand(String name) {
        brands.add(new Brand(++lastBrandId, name));
    }

    public void deleteBrand(Brand brand) {
        brands.remove(brand);
    }

    public Brand addAndGetBrand(String name){
        Brand brand = new Brand(++lastBrandId,name);
        brands.add(brand);
        return brand;
    }
}
