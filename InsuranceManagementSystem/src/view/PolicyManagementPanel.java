package view;

import manager.AccountManager;
import manager.AddressManager;
import model.User;
import model.accounts.Account;
import model.accounts.EnterpriseAccount;
import model.accounts.IndividualAccount;
import model.addresses.Address;
import model.addresses.BusinessAddress;
import model.addresses.HomeAddress;
import model.exceptions.InvalidAuthenticationException;
import model.insurances.*;

import java.awt.desktop.AppReopenedEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PolicyManagementPanel {
    private final Scanner scanner = new Scanner(System.in);
    private final AccountManager accountManager;
    public PolicyManagementPanel() throws InvalidAuthenticationException{
        accountManager = new AccountManager();
        guestUserMenu();
    }

    public void guestUserMenu() throws InvalidAuthenticationException{
        String str = "--------------------------------------\n" +
                "Insurance Management Panel\n" +
                "1 - Sign In\n" +
                "2 - Register\n" +
                "0 - Exit The Program\n" +
                "--------------------------------------";
        System.out.println(str);

        int preference = getIntegerFromMinToMaxFromUser(0,2,"Your Preference : ");

        switch (preference){
            case 0:
                exitTheProgram();
                break;
            case 1:
                signInMenu();
                break;
            case 2:
                createNewUserAndAccountMenu();
                break;
        }
    }

    public void createNewUserAndAccountMenu() throws InvalidAuthenticationException{
        System.out.println("--------------------------------------\nCreate New User Menu...");

        System.out.println("Enter Your Email : ");
        String email = scanner.nextLine();

        if(accountManager.isExist(email)){
            System.out.println("This Email Address is Already Registered.");
            guestUserMenu();
            return;
        }

        System.out.println("Enter Your Password : ");
        String password = scanner.nextLine();

        int preference = getIntegerFromMinToMaxFromUser(1,2,"Select the Account Type\n" +
                "1 - Individual Account\n2 - Enterprise Account\n");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        Account account;
        if (preference == 1) account = new IndividualAccount(user);
        else account = new EnterpriseAccount(user);

        accountManager.addNewAccount(account);

        System.out.println("Enter Your First Name : ");
        String firstName = scanner.nextLine();
        user.setFirstName(firstName);

        System.out.println("Enter Your Last Name : ");
        String lastName = scanner.nextLine();
        user.setLastName(lastName);

        System.out.println("Enter Your Profession : ");
        String profession = scanner.nextLine();
        user.setProfession(profession);

        int age = getIntegerFromMinToMaxFromUser(1,120,"Enter Your Age : \n");
        user.setAge(age);

        System.out.println("Enter Your Home Address : ");
        String homeAddress = scanner.nextLine();
        Address home = new HomeAddress(homeAddress);
        AddressManager.addAddress(user,home);

        System.out.println("Enter Your Business Address : ");
        String businessAddress = scanner.nextLine();
        Address business = new BusinessAddress(businessAddress);
        AddressManager.addAddress(user,business);

        registeredUserMenu(account);
    }

    private void signInMenu() throws InvalidAuthenticationException {
        SignInAssistant signInAssistant = new SignInAssistant();

        Account account;

        try{
            account = accountManager.login(signInAssistant.getEmail(), signInAssistant.getPassword());
            registeredUserMenu(account);
        }catch (InvalidAuthenticationException e){
            System.out.println("Account not Found!");
            guestUserMenu();
        }
    }

    private void registeredUserMenu(Account account) throws InvalidAuthenticationException {
        User user = account.getUser();

        if (account.getAuthenticationStatus() == Account.AuthenticationStatus.FAIL){
            account.setAuthenticationStatus(Account.AuthenticationStatus.SUCCESS);
            user.setLastEntryDate(new Date());
        }
        String str =
                "--------------------------------------\n" +
                        "Insurance Management Panel\n" +
                        "1 - My User Information\n" +
                        "2 - My Policies\n" +
                        "3 - Add a New Insurance Policy\n" +
                        "4 - Sign Out\n" +
                        "0 - Exit The Program\n" +
                        "--------------------------------------";
        System.out.println(str);

        int preference = getIntegerFromMinToMaxFromUser(0,4,"Your Preference : ");

        switch (preference){
            case 0:
                exitTheProgram();
                break;
            case 1:
                showAccountInfo(account);
                break;
            case 2:
                printAllPolicies(account);
                break;
            case 3:
                addNewInsuranceMenu(account);
                break;
            case 4:
                signOut();
                break;
        }
    }

    private void showAccountInfo(Account account) throws InvalidAuthenticationException {
        account.showUserInfo();
        registeredUserMenu(account);
    }

    private void printAllPolicies(Account account) throws InvalidAuthenticationException {
        account.printPolicies();
        registeredUserMenu(account);
    }

    private void addNewInsuranceMenu(Account account) throws InvalidAuthenticationException{
        String str =
                "--------------------------------------\n" +
                        "Insurance Purchase Menu\n" +
                        "1 - Buy Health Insurance\n" +
                        "2 - Buy Residence Insurance\n" +
                        "3 - Buy Travel Insurance\n" +
                        "4 - Buy Car Insurance\n" +
                        "0 - Back To Main Menu\n" +
                        "--------------------------------------";
        System.out.println(str);

        int preference = getIntegerFromMinToMaxFromUser(0,4, "Your Preference: ");

        if(preference == 0) registeredUserMenu(account);
        else buyANewInsurance(account, preference);
    }

    private void buyANewInsurance(Account account,int insurancNo) throws InvalidAuthenticationException {
        Insurance insurance;
        switch (insurancNo){
            case 1:
                insurance = new HealthInsurance();
                break;
            case 2:
                insurance = new ResidenceInsurance();
                break;
            case 3:
                insurance = new TravelInsurance();
                break;
            default:
                insurance = new CarInsurance();
        }

        Calendar calendar = Calendar.getInstance();
        Date start = calendar.getTime();

        calendar.add(Calendar.YEAR,1);
        Date end = calendar.getTime();

        insurance.setStartDate(start);
        insurance.setEndDate(end);

        System.out.println(insurance.print());

        System.out.println("Do You Want to Buy? (1-Yes, 2-No) ");
        int preference = getIntegerFromMinToMaxFromUser(1,2,"Your Preference : ");
        if (preference == 1){
            account.addInsurance(insurance);
            System.out.println(insurance.getName() + " was Purchased.");
        }else System.out.println("The Purchase was Abandoned.");

        registeredUserMenu(account);
    }
    private void signOut() throws InvalidAuthenticationException{
        guestUserMenu();
    }
    private void exitTheProgram(){
        System.out.println("Exiting the Program...");
    }

    private int getIntegerFromMinToMaxFromUser(int min, int max, String repeatingMessage){
        int selection;
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.print(repeatingMessage);
            try {
                selection = scanner.nextInt();
                if (selection >= min && selection <= max)
                    break;
                else System.out.println("Invalid entry!");
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry!");
                scanner.next();
            }
        }
        return selection;
    }
}
