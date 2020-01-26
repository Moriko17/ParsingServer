package me.moriko17.parsingServer.service;

import me.moriko17.parsingServer.models.AnimeToSubscribe;
import me.moriko17.parsingServer.models.SubscribeDto;

import java.io.IOException;
import java.util.List;

public interface TargetService {
    Long subscribe(AnimeToSubscribe animeToSubscribe) throws IOException;
    Long unsubscribe(Long id);
    List<SubscribeDto> getSubscribes();
}
