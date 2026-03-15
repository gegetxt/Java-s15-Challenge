package com.library.member;

import com.library.enums.MemberType;

public class Faculty extends Reader {

    public Faculty(int id, String name, String phone, String address) {
        super(id, name, phone, address, MemberType.FACULTY, 5);
    }

    @Override
    public void whoYouAre() {
        System.out.println("I am a faculty member. My name is " + getName());
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", borrowedBookCount=" + getBorrowedBooks().size() +
                ", maxBookLimit=" + getMaxBookLimit() +
                '}';
    }
}
