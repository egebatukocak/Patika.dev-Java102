public class Brand implements Comparable<Brand>{
    private int brandId;
    private String brandName;

    public Brand(int id, String name) {
        this.brandId = id;
        this.brandName = name;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int id) {
        this.brandId = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String name) {
        this.brandName = name;
    }

    @Override
    public int compareTo(Brand other) {
        return this.brandName.compareTo(other.getBrandName());
    }
}
