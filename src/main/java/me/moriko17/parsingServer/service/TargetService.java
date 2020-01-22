package me.moriko17.parsingServer.service;

import me.moriko17.parsingServer.models.AnimeDto;
import me.moriko17.parsingServer.models.AnimeToSubscribe;

import java.io.IOException;
import java.util.List;

public interface TargetService {
    int getItemsCount(String targetUrl, String targetPlayer, String targetVoice) throws IOException;
    int getItemsCount(Long id) throws IOException;
    List<String> getVariants(String targetUrl) throws IOException;
    Long titleSubscribe(AnimeToSubscribe animeToSubscribe);
    List<AnimeDto> fetchSubscribeList();
}
