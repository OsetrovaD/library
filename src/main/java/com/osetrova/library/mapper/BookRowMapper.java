package com.osetrova.library.mapper;

import com.osetrova.library.dto.BookSearchDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<BookSearchDto> {

    @Override
    public BookSearchDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BookSearchDto.builder()
                .name(rs.getString("name"))
                .genreName(rs.getString("genre"))
                .authorName(rs.getString("author"))
                .creationYear(rs.getInt("creation_year"))
                .build();
    }
}
