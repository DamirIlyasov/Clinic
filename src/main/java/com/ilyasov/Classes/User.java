package com.ilyasov.Classes;

/**
 * Created by damir on 09.11.16.
 */
public class User {
    public User(){

    }
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String age;
    int status;
    public User(int status){
        this.status = status;
    }
    public User(int status, String firstName){
        this.status = status;
        this.firstName = firstName;
    }
    public User(int status, String firstName, String lastName, String phoneNumber, String email, String age){
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
    }

    public int getStatus() {
        return status;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
