package com.osetrova.library.controller;

import com.osetrova.library.dto.BookSearchDto;
import com.osetrova.library.dto.SearchParameterDto;
import com.osetrova.library.service.BookSearchService;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookSearchController {

    private static final String[] HEADERS = {"name", "creation year", "genre", "author"};
    private BookSearchService service;

    @PostMapping(value = "/book-search")
    public void get(SearchParameterDto parameter, HttpServletResponse response) {
        if (areParameterValuesValid(parameter)) {
            List<BookSearchDto> query = service.getBooks(parameter);
            response.setContentType("text/plain; charset=utf-8");
            try (CSVPrinter printer = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT.withHeader(HEADERS))) {
                query.forEach(x -> printValue(printer, x));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean areParameterValuesValid(SearchParameterDto parameter) {
        boolean result = false;
        if (hasDatePeriodSearchParameter(parameter)) {
            result = parameter.getDatePeriodStartYear() <= parameter.getDatePeriodEndYear();
        } else {
            if (parameter.getDatePeriodStartYear() == null && parameter.getDatePeriodEndYear() == null) {
                result = parameter.hasAnySearchParameter();
            }
        }
        return result;
    }

    private boolean hasDatePeriodSearchParameter(SearchParameterDto parameter) {
        return parameter.getDatePeriodStartYear() != null && parameter.getDatePeriodEndYear() != null;
    }

    private void printValue(CSVPrinter printer, BookSearchDto bookInfo) {
        try {
            printer.printRecord(bookInfo.getName(), bookInfo.getCreationYear(), bookInfo.getGenreName(), bookInfo.getAuthorName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
