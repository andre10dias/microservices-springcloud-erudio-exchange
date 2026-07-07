package com.github.andre10dias.controller;

import com.github.andre10dias.enviroment.InstanceInformationService;
import com.github.andre10dias.model.Book;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book-service")
public class BookController {
    private final InstanceInformationService infoService;

    public BookController(InstanceInformationService infoService) {
        this.infoService = infoService;
    }

    @GetMapping(value = "/{id}/{currency}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ) {
        return new Book();
    }
}
