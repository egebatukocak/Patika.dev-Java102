package model.accounts;

import model.User;
import model.insurances.Insurance;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    public enum AuthenticationStatus {FAIL, SUCCESS}

    private AuthenticationStatus authenticationStatus = AuthenticationStatus.FAIL;
    private List<Insurance> insuranceList = new ArrayList<>();
    private final User user;

    public Account(User user) {
        this.user = user;
    }

    public final void showUserInfo() {
        System.out.println("USER INFO");
        this.user.printUserInfo();
    }

    public AuthenticationStatus getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(AuthenticationStatus authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public List<Insurance> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void printPolicies() {
        if (insuranceList.size() == 0) {
            System.out.println("You have no insurance.");
        } else {
            for (Insurance i : insuranceList) {
                System.out.println(i.print());
            }
        }
    }

    public void addInsurance(Insurance insurance) {
        insuranceList.add(insurance);
    }
}
