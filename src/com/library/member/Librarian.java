package com.library.member;

public class Librarian extends Person {

    private String phone;
    private String address;
    private String employeeNumber;
    private String password;

    public Librarian(int id, String name, String phone, String address, String employeeNumber, String password) {
        super(id, name);
        this.phone = phone;
        this.address = address;
        this.employeeNumber = employeeNumber;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.isBlank()) {
            this.phone = phone;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null && !address.isBlank()) {
            this.address = address;
        }
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        if (employeeNumber != null && !employeeNumber.isBlank()) {
            this.employeeNumber = employeeNumber;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null && !password.isBlank()) {
            this.password = password;
        }
    }

    public boolean login(String enteredPassword) {
        return password != null && password.equals(enteredPassword);
    }

    @Override
    public void whoYouAre() {
        System.out.println("I am a librarian. My name is " + getName());
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                '}';
    }
}