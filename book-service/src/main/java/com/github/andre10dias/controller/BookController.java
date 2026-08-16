package com.github.andre10dias.controller;

import com.github.andre10dias.dto.ExchangeDto;
import com.github.andre10dias.enviroment.InstanceInformationService;
import com.github.andre10dias.model.Book;
import com.github.andre10dias.repository.BookRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("book-service")
public class BookController {
    private final InstanceInformationService infoService;
    private final BookRepository repository;

    private final String EXCHANGE_BASE_URL = "http://localhost:8000/exchange-service/";

    public BookController(
            InstanceInformationService infoService,
            BookRepository repository)
    {
        this.infoService = infoService;
        this.repository = repository;
    }

    @GetMapping(value = "/{id}/{currency}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ) {
        String port = infoService.retrieveServerPort();
        var book = repository.findById(id).orElseThrow();

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var response = new RestTemplate()
                .getForEntity(EXCHANGE_BASE_URL +
                        "{amount}/{from}/{to}", ExchangeDto.class, params);

        ExchangeDto exchangeDto = response.getBody();
        book.setEnvironment(port);
        assert exchangeDto != null;
        book.setPrice(exchangeDto.conversionValue());
        book.setCurrency(currency);
        return book;
    }
}
