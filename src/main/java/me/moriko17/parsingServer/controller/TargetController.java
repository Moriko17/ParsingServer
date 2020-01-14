package me.moriko17.parsingServer.controller;

import me.moriko17.parsingServer.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class TargetController {
    private TargetService targetService;

    @Autowired
    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    @GetMapping("/target")
    public int getItemsCount(String url) throws IOException {
        return targetService.getItemsCount("");
    }

    @GetMapping("/targetVar")
    public List<String> getVariants() throws IOException {
        return targetService.getVariants();
    }
}
