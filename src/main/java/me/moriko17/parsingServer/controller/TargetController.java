package me.moriko17.parsingServer.controller;

import me.moriko17.parsingServer.models.AnimeDto;
import me.moriko17.parsingServer.models.AnimeToSubscribe;
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

    @PostMapping("/subscribe")
    public Long titleSubscribe(@RequestBody AnimeToSubscribe animeToSubscribe) throws IOException {
        return targetService.titleSubscribe(animeToSubscribe);
    }

    @GetMapping("/subscribe")
    public List<AnimeDto> fetchSubscribeList() {
        return targetService.fetchSubscribeList();
    }

    @GetMapping("/subscribe/{id}")
    public int getItemsCount(@PathVariable(value = "id") Long id) throws IOException {
        return targetService.getItemsCount(id);
    }

    @DeleteMapping("/subscribe/{id}")
    public Long titleUnsubscribe(@PathVariable(value = "id") Long id) {
        return targetService.titleUnsubscribe(id);
    }
}
