package com.rohan.springcloud.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    private static void exception() {
        throw new RuntimeException("Invalid Data");
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from,to);

        Optional.ofNullable(currencyExchange).ifPresentOrElse((value) -> {
            String port = environment.getProperty("local.server.port");
            value.setEnvironment(port);
        }, CurrencyExchangeController::exception);


        return currencyExchange;
    }




}
