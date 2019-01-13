package com.osetrova.library.service;

import com.osetrova.library.dao.BookSearchDao;
import com.osetrova.library.dto.BookSearchDto;
import com.osetrova.library.dto.SearchParameterDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookSearchService {

    private BookSearchDao searchDao;

    public List<BookSearchDto> getBooks(SearchParameterDto parameterDto) {
        return searchDao.getBooks(parameterDto);
    }
}
