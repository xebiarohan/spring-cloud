package com.rohan.springcloud.currencyconversion.proxy;

import com.rohan.springcloud.currencyconversion.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// here the name is then name of the component that we want to connect
//
@FeignClient(name="currency-exchange", url = "localhost:8000")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
