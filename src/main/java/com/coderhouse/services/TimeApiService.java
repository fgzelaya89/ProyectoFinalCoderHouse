package com.coderhouse.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class TimeApiService {

    @Autowired
    private RestTemplate restTemplate;


    private final String TIME_API_URL = "https://timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires";

    public  LocalDateTime obtenerFechaActual() {
        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(TIME_API_URL, String.class);

            if (response != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(response);

                String fechaHora = jsonNode.get("dateTime").asText();
                return LocalDateTime.parse(fechaHora, DateTimeFormatter.ISO_DATE_TIME);
            }
        } catch (Exception e) {
            System.out.println("Fallo al obtener la fecha del API, se usará la fecha local. Error: " + e.getMessage());
        }
        return LocalDateTime.now();
    }
}