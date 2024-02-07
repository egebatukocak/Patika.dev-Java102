package model.accounts;

import model.User;

public class IndividualAccount extends Account implements Comparable<IndividualAccount> {
    public IndividualAccount(User user) {
        super(user);
    }

    @Override
    public void printPolicies() {
        System.out.println("Your Individual Policies...");
        super.printPolicies();
    }

    @Override
    public int compareTo(IndividualAccount o) {
        return 0;
    }
}
