package com.example.springserver.models;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PowerData {
    private int pesId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime datetimeGMT;
    private double generationMW;
    public PowerData(){

    }
    public PowerData(int pesId, LocalDateTime datetimeGMT, double generationMW) {
        this.pesId = pesId;
        this.datetimeGMT = datetimeGMT;
        this.generationMW = generationMW;
    }

    // Getters and setters
    public int getPesId() {
        return pesId;
    }

    public void setPesId(int pesId) {
        this.pesId = pesId;
    }

    public LocalDateTime getDatetimeGMT() {
        return datetimeGMT;
    }

    public void setDatetimeGMT(LocalDateTime datetimeGMT) {
        this.datetimeGMT = datetimeGMT;
    }

    public double getGenerationMW() {
        return generationMW;
    }

    public void setGenerationMW(double generationMW) {
        this.generationMW = generationMW;
    }

}
