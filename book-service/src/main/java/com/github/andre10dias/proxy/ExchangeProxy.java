package com.github.andre10dias.proxy;

import com.github.andre10dias.dto.ExchangeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchange-service", url = "localhost:8000")
public interface ExchangeProxy {
    @GetMapping(value = "/exchange-service/{amount}/{from}/{to}")
    public ExchangeDto getExchange(
            @PathVariable("amount") Double amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to);
}
