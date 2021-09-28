package com.ben.bookingexamplemaven.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.ben.bookingexamplemaven.client.model.SettingsDto;
import com.ben.bookingexamplemaven.client.model.SettingsResponse;
import com.ben.bookingexamplemaven.config.FeignClientConfig;

@FeignClient(name = "AirportInfoServiceClient",
        url = "${application.services.airport-info-system}",
        configuration = FeignClientConfig.class)
public interface AirportInfoServiceClient {
    @PostMapping(value = "/flight/{flightId}/settings")
    SettingsResponse takeSettings(@PathVariable String flightId, @RequestBody SettingsDto settingsDto);
}
