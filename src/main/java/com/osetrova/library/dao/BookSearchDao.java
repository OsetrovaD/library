package com.osetrova.library.dao;

import com.osetrova.library.dto.BookSearchDto;
import com.osetrova.library.dto.SearchParameterDto;
import com.osetrova.library.mapper.BookRowMapper;
import com.osetrova.library.util.StringReplaceUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookSearchDao {

    private static final String QUERY =
            "SELECT b.name, b.creation_year, g.name AS genre, a.name AS author FROM library_app_storage.book b" +
            " JOIN library_app_storage.genre g ON g.id = b.genre_id" +
            " JOIN library_app_storage.author a ON a.id = b.author_id" +
            " WHERE";
    private static final Map<String, String> QUERY_PARAMETER = new HashMap<>();
    private static final String AND = " AND";
    private JdbcTemplate template;

    static {
        QUERY_PARAMETER.put("authorName", " a.name = '?'");
        QUERY_PARAMETER.put("genreName", " UPPER(g.name) LIKE UPPER('%?%')");
        QUERY_PARAMETER.put("datePeriodStartYear", " b.creation_year BETWEEN ?");
        QUERY_PARAMETER.put("datePeriodEndYear", " ?");
    }

    public List<BookSearchDto> getBooks(SearchParameterDto parameterDto) {
        addQueryParameter(parameterDto);
        return template.query(addQueryParameter(parameterDto), new BookRowMapper());
    }

    private String addQueryParameter(SearchParameterDto parameter) {
        List<String> fieldValues = parameter.getValidFieldValues();
        List<String> validFieldNames = parameter.getValidFieldNames();
        StringBuilder queryBuilder = new StringBuilder(QUERY);

        queryBuilder.append(setSearchValue(QUERY_PARAMETER.get(validFieldNames.get(0)), fieldValues.get(0)));

        if (fieldValues.size() > 1) {
            fieldValues = fieldValues.subList(1, fieldValues.size());
            validFieldNames = validFieldNames.subList(1, validFieldNames.size());

            for (int i = 0; i < fieldValues.size(); i++) {
                queryBuilder.append(AND);
                queryBuilder.append(setSearchValue(QUERY_PARAMETER.get(validFieldNames.get(i)), fieldValues.get(i)));
            }
        }

        return queryBuilder.toString();
    }

    private String setSearchValue(String queryPart, String value) {
        return StringReplaceUtil.replaceQuestionMark(queryPart, value);
    }
}
