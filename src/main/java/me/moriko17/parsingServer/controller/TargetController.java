package me.moriko17.parsingServer.controller;

import me.moriko17.parsingServer.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public int getItemsCount(@RequestParam String targetUrl,
                             @RequestParam String targetPlayer,
                             @RequestParam String targetVoice) throws IOException {
        return targetService.getItemsCount(targetUrl, targetPlayer, targetVoice);
    }

    @GetMapping("/targetVar")
    public List<String> getVariants(@RequestParam String targetUrl) throws IOException {
        return targetService.getVariants(targetUrl);
    }
}
