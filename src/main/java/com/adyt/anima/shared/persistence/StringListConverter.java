package com.adyt.anima.shared.persistence;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> strings) {

        if (strings == null) {
            return null;
        }
        if (strings.isEmpty()) {
            return "";
        }

        return String.join(",", strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {

        if (s == null) {
            return null;
        }

        if (s.length() == 0){
            return new ArrayList<>();
        }

        return Arrays.asList(s.split(","));
    }
}
