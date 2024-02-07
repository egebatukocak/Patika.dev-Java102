package model.addresses;

public class HomeAddress implements Address {
    String homeAddress;

    public HomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public String getAddress() {
        return homeAddress;
    }

    @Override
    public void setAddres(String addresStr) {
        this.homeAddress = addresStr;
    }
}
