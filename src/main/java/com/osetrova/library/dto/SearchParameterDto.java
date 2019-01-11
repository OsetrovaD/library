package com.osetrova.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchParameterDto {

    private String authorName;
    private String genreName;
    private Integer datePeriodStartYear;
    private Integer datePeriodEndYear;

    public boolean hasAnySearchParameter() {
        return Arrays.stream(getClass().getDeclaredFields()).anyMatch(this::isValid);
    }

    public List<String> getValidFieldValues() {
        List<String> fieldValues = new ArrayList<>();
        getValidFields().forEach(x -> addValueToList(fieldValues, x));

        return fieldValues;
    }

    public List<String> getValidFieldNames() {
        List<String> fieldValues = new ArrayList<>();
        getValidFields().forEach(x -> fieldValues.add(x.getName()));

        return fieldValues;
    }

    private List<Field> getValidFields() {
        return Arrays.stream(getClass().getDeclaredFields())
                .filter(this::isValid)
                .sorted(Comparator.comparing(Field::getName).reversed())
                .collect(Collectors.toList());
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
