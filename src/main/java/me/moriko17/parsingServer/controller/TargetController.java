package me.moriko17.parsingServer.controller;

import me.moriko17.parsingServer.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public int getItemsCount(@RequestBody String targetUrl,
                             @RequestBody String targetPlayer,
                             @RequestBody String targetVoice) throws IOException {
        return targetService.getItemsCount(targetUrl, targetPlayer, targetVoice);
    }

    @GetMapping("/targetVar/{targetUrl}")
    public List<String> getVariants(@PathVariable String targetUrl) throws IOException {
        return targetService.getVariants(targetUrl);
    }
}
