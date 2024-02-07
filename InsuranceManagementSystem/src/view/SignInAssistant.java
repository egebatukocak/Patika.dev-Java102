package view;

import java.util.Scanner;

public class SignInAssistant {
    private Scanner scanner = new Scanner(System.in);

    private String email, password;

    public SignInAssistant() {
        signIn();
    }

    private void signIn() {
        System.out.println("Please enter your e-mail : ");
        email = scanner.nextLine().trim();

        System.out.println("Please enter your password : ");
        password = scanner.nextLine().trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
