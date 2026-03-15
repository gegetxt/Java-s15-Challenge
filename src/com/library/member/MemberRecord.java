package com.library.member;

import com.library.enums.MemberType;

import java.time.LocalDate;

public class MemberRecord {

    private int id;
    private Reader reader;
    private MemberType memberType;
    private LocalDate membershipDate;
    private int maxBookLimit;
    private int currentBorrowedBookCount;
    private boolean active;

    public MemberRecord(int id, Reader reader, MemberType memberType, LocalDate membershipDate, int maxBookLimit) {
        this.id = id;
        this.reader = reader;
        this.memberType = memberType;
        this.membershipDate = membershipDate;
        this.maxBookLimit = maxBookLimit;
        this.currentBorrowedBookCount = 0;
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        if (reader != null) {
            this.reader = reader;
        }
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        if (memberType != null) {
            this.memberType = memberType;
        }
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        if (membershipDate != null) {
            this.membershipDate = membershipDate;
        }
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public void setMaxBookLimit(int maxBookLimit) {
        if (maxBookLimit > 0) {
            this.maxBookLimit = maxBookLimit;
        }
    }

    public int getCurrentBorrowedBookCount() {
        return currentBorrowedBookCount;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivateMembership() {
        this.active = false;
    }

    public void activateMembership() {
        this.active = true;
    }

    public boolean canBorrowMoreBooks() {
        return active && currentBorrowedBookCount < maxBookLimit;
    }

    public void increaseBorrowedBookCount() {
        if (currentBorrowedBookCount < maxBookLimit) {
            currentBorrowedBookCount++;
        }
    }

    public void decreaseBorrowedBookCount() {
        if (currentBorrowedBookCount > 0) {
            currentBorrowedBookCount--;
        }
    }

    @Override
    public String toString() {
        return "MemberRecord{" +
                "id=" + id +
                ", reader=" + reader.getName() +
                ", memberType=" + memberType +
                ", membershipDate=" + membershipDate +
                ", maxBookLimit=" + maxBookLimit +
                ", currentBorrowedBookCount=" + currentBorrowedBookCount +
                ", active=" + active +
                '}';
    }
}