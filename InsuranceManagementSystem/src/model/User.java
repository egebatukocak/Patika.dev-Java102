package model;

import model.addresses.Address;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private String firstName, lastName, email, password, profession;
    private int age;
    private Date lastEntryDate;
    private List<Address> addressList = new ArrayList<>();

    public void printUserInfo() {
        String info = "Name: " + firstName +
                "\nSurname: " + lastName +
                "\ne-mail: " + email +
                "\nPassword: " + password +
                "\nProfession: " + profession +
                "\nAge: " + age +
                "\nHome Address: " + addressList.get(0).getAddress() +
                "\nBusiness Address: " + addressList.get(1).getAddress() +
                "\nLast Entry Date: " + lastEntryDate;
        System.out.println(info);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getLastEntryDate() {
        return lastEntryDate;
    }

    public void setLastEntryDate(Date lastEntryDate) {
        this.lastEntryDate = lastEntryDate;
    }

    public List<Address> getAddressList() {
        return addressList;
    }
}
