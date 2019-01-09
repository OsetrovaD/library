package com.osetrova.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchParameterDto {

    private String authorName;
    private String genreName;
    private Integer startYear;
    private Integer endYear;

    public boolean hasAnySearchParameter() {
        return Arrays.stream(getClass().getDeclaredFields()).anyMatch(this::isValid);
    }

    public List<String> getValidFieldValues() {
        List<String> fieldValues = new ArrayList<>();
        Arrays.stream(getClass().getDeclaredFields()).filter(this::isValid).forEach(x -> addValueToList(fieldValues, x));

        return fieldValues;
    }

    public List<String> getValidFieldNames() {
        List<String> fieldValues = new ArrayList<>();
        Arrays.stream(getClass().getDeclaredFields()).filter(this::isValid).forEach(x -> fieldValues.add(x.getName()));

        return fieldValues;
    }

    private boolean isValid(Field field) {
        boolean result = false;
        try {
            result = field.get(this) != null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void addValueToList(List<String> fieldValues, Field field) {
        try {
            fieldValues.add(field.get(this).toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
