package manager;

import model.User;
import model.addresses.Address;
import model.addresses.BusinessAddress;
import model.addresses.HomeAddress;

public class AddressManager {
    public static void addAddress(User user, Address address) {
        if (address.getClass() == HomeAddress.class) {
            System.out.println("Inserted home address");
        } else if (address.getClass() == BusinessAddress.class) {
            System.out.println("Inserted business address");
        }

        user.getAddressList().add(address);
    }

    public static void deleteAddress(User user, Address address) {
        user.getAddressList().remove(address);
    }
}
