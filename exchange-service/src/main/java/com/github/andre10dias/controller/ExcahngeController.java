package com.github.andre10dias.controller;

import com.github.andre10dias.enviroment.InstanceInformationService;
import com.github.andre10dias.model.Exchange;
import com.github.andre10dias.repository.ExchangeRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("exchange-service")
public class ExcahngeController {

    private final InstanceInformationService infoService;
    private final ExchangeRepository repository;

    public ExcahngeController(
            InstanceInformationService infoService,
            ExchangeRepository repository
    ) {
        this.infoService = infoService;
        this.repository =  repository;
    }

    @GetMapping(value = "/{amount}/{from}/{to}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Exchange getExchange(
            @PathVariable("amount") BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to) {

        Exchange exchange = repository.findByFromAndTo(from, to);
        if (exchange == null) throw new RuntimeException("Currency Unsupported");

        BigDecimal conversionFactory = exchange.getConversionFactor();
        BigDecimal convertedValue = conversionFactory.multiply(amount);
        exchange.setConversionValue(convertedValue);
        exchange.setEnviroment("PORT " + infoService.retrieveServerPort());

        return exchange;
    }

}
