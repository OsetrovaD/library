package com.osetrova.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookSearchDto {

    private String name;
    private String genreName;
    private String authorName;
    private Integer creationYear;
}