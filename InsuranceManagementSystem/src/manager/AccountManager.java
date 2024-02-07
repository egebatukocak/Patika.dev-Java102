package manager;

import model.User;
import model.accounts.Account;
import model.exceptions.InvalidAuthenticationException;

import java.util.TreeSet;

public class AccountManager implements Comparable<Account>{
    private TreeSet<Account> accounts = new TreeSet<>();
    public Account login(String email,String password) throws InvalidAuthenticationException {
        for (Account account : accounts){
            User user = account.getUser();

            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                return account;
            }
        }
        throw new InvalidAuthenticationException("Account not found!");
    }

    public boolean isExist(String email){
        for (Account account : accounts){
            if (account.getUser().getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }

    public void addNewAccount(Account account){
        accounts.add(account);
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }
}
