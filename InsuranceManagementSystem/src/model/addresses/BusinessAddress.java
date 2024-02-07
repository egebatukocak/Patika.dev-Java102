package model.addresses;

public class BusinessAddress implements Address {
    String businessAddress;

    public BusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    @Override
    public String getAddress() {
        return businessAddress;
    }

    @Override
    public void setAddres(String addresStr) {
        this.businessAddress = addresStr;
    }
}
