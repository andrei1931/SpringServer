package com.example.springserver.controllers;
import com.example.springserver.PowerGeneration;
import com.example.springserver.models.PowerData;
import com.example.springserver.services.PowerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PowerController {

    private final PowerService powerService;

    public PowerController(PowerService powerService) {
        this.powerService = powerService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("powerData", powerService.getPowerData());
        return "index";
    }
    @ResponseBody
    @GetMapping("/api/power")
    public List<PowerGeneration> getPowerDataForAndroid() {
        List<PowerData> powerDataList = powerService.getPowerData();
        List<PowerGeneration> powerGenerationList = new ArrayList<>();

        for (PowerData powerData : powerDataList) {
            double generationMW = powerData.getGenerationMW();
            PowerGeneration powerGeneration = new PowerGeneration(generationMW);
            powerGenerationList.add(powerGeneration);
        }

        return powerGenerationList;
    }

}
