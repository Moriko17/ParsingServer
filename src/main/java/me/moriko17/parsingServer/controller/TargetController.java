package me.moriko17.parsingServer.controller;

import me.moriko17.parsingServer.models.SubscribeDto;
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

    @PostMapping("/subscribe")
    public Long subscribe(@RequestBody AnimeToSubscribe animeToSubscribe) throws IOException {
        return targetService.subscribe(animeToSubscribe);
    }

    @GetMapping("/subscribe")
    public List<SubscribeDto> getSubscribes() {
        return targetService.getSubscribes();
    }

    @DeleteMapping("/subscribe/{id}")
    public Long unsubscribe(@PathVariable(value = "id") Long id) {
        return targetService.unsubscribe(id);
    }
}
