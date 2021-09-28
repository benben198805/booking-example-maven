package com.ben.bookingexamplemaven.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ItWireMockConfig {
    @Autowired
    private WireMockServer wireMockServer;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public WireMockServer mockAirportInfoService() {
        return new WireMockServer(8099);
    }
}
