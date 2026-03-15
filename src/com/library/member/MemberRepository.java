package com.library.member;

import com.library.enums.MemberType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private Map<Integer, Reader> readers;

    public MemberRepository() {
        this.readers = new HashMap<>();
    }

    public void save(Reader reader) {
        if (reader != null) {
            readers.put(reader.getId(), reader);
        }
    }

    public Reader findById(int id) {
        return readers.get(id);
    }

    public List<Reader> findAll() {
        return new ArrayList<>(readers.values());
    }

    public boolean deleteById(int id) {
        return readers.remove(id) != null;
    }

    public boolean existsById(int id) {
        return readers.containsKey(id);
    }

    public List<Reader> findByName(String name) {
        List<Reader> result = new ArrayList<>();

        if (name == null || name.isBlank()) {
            return result;
        }

        for (Reader reader : readers.values()) {
            if (reader.getName().equalsIgnoreCase(name)) {
                result.add(reader);
            }
        }

        return result;
    }

    public List<Reader> findByType(MemberType memberType) {
        List<Reader> result = new ArrayList<>();

        if (memberType == null) {
            return result;
        }

        for (Reader reader : readers.values()) {
            if (reader.getMemberType() == memberType) {
                result.add(reader);
            }
        }

        return result;
    }
}