package com.example.springserver.services;
import com.example.springserver.models.PowerData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PowerService {

    private static final String API_URL = "https://api.solar.sheffield.ac.uk/pvlive/api/v4/pes/10?start=2024-04-02T10:58:00&end=2024-04-03T13:30:00";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PowerService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<PowerData> getPowerData() {
        JsonNode jsonResponse = restTemplate.getForObject(API_URL, JsonNode.class);
        JsonNode jsonData = jsonResponse.get("data");

        List<PowerData> powerDataList = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneOffset.UTC);

        for (JsonNode dataItem : jsonData) {
            int pesId = dataItem.get(0).asInt();
            String datetimeGMTString = dataItem.get(1).asText();
            OffsetDateTime datetimeGMT = OffsetDateTime.parse(datetimeGMTString, dateTimeFormatter);
            double generationMW = dataItem.get(2).asDouble();

            PowerData powerData = new PowerData(pesId, datetimeGMT.toLocalDateTime(), generationMW);
            powerDataList.add(powerData);
        }

        return powerDataList;
    }
}