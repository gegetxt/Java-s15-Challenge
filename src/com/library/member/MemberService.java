package com.library.member;

import com.library.enums.MemberType;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberService {

    private MemberRepository memberRepository;
    private Map<Integer, MemberRecord> memberRecords;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.memberRecords = new HashMap<>();
    }

    public void addReader(Reader reader) {
        if (reader == null) {
            System.out.println("Reader cannot be null.");
            return;
        }

        if (memberRepository.existsById(reader.getId())) {
            System.out.println("A reader with this id already exists.");
            return;
        }

        memberRepository.save(reader);

        MemberRecord memberRecord = new MemberRecord(
                reader.getId(),
                reader,
                reader.getMemberType(),
                LocalDate.now(),
                reader.getMaxBookLimit()
        );

        memberRecords.put(reader.getId(), memberRecord);

        System.out.println("Reader added successfully.");
    }

    public Reader getReaderById(int id) {
        Reader reader = memberRepository.findById(id);

        if (reader == null) {
            System.out.println("Reader not found.");
        }

        return reader;
    }

    public List<Reader> getAllReaders() {
        return memberRepository.findAll();
    }

    public List<Reader> getReadersByName(String name) {
        return memberRepository.findByName(name);
    }

    public List<Reader> getReadersByType(MemberType memberType) {
        return memberRepository.findByType(memberType);
    }

    public void deleteReader(int id) {
        Reader reader = memberRepository.findById(id);

        if (reader == null) {
            System.out.println("Reader not found.");
            return;
        }

        if (!reader.getBorrowedBooks().isEmpty()) {
            System.out.println("Reader cannot be deleted because there are borrowed books.");
            return;
        }

        memberRepository.deleteById(id);
        memberRecords.remove(id);

        System.out.println("Reader deleted successfully.");
    }

    public MemberRecord getMemberRecordByReaderId(int readerId) {
        return memberRecords.get(readerId);
    }

    public void updateBorrowedBookCount(int readerId, boolean increase) {
        MemberRecord memberRecord = memberRecords.get(readerId);

        if (memberRecord == null) {
            System.out.println("Member record not found.");
            return;
        }

        if (increase) {
            memberRecord.increaseBorrowedBookCount();
        } else {
            memberRecord.decreaseBorrowedBookCount();
        }
    }

    public void deactivateMember(int readerId) {
        MemberRecord memberRecord = memberRecords.get(readerId);

        if (memberRecord == null) {
            System.out.println("Member record not found.");
            return;
        }

        memberRecord.deactivateMembership();
        System.out.println("Membership deactivated.");
    }

    public void activateMember(int readerId) {
        MemberRecord memberRecord = memberRecords.get(readerId);

        if (memberRecord == null) {
            System.out.println("Member record not found.");
            return;
        }

        memberRecord.activateMembership();
        System.out.println("Membership activated.");
    }
}
