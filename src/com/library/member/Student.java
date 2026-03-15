package com.library.member;

import com.library.enums.MemberType;

public class Student extends Reader {

    public Student(int id, String name, String phone, String address) {
        super(id, name, phone, address, MemberType.STUDENT, 5);
    }

    @Override
    public void whoYouAre() {
        System.out.println("I am a student. My name is " + getName());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", borrowedBookCount=" + getBorrowedBooks().size() +
                ", maxBookLimit=" + getMaxBookLimit() +
                '}';
    }
}
