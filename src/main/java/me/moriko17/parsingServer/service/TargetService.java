package me.moriko17.parsingServer.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import me.moriko17.parsingServer.models.AnimeDto;
import me.moriko17.parsingServer.models.AnimeToSubscribe;

import java.io.IOException;
import java.util.List;

public interface TargetService {
    int getItemsCount(String targetUrl, String targetPlayer, String targetVoice) throws IOException, UnirestException;
    int getItemsCount(Long id) throws IOException, UnirestException;
    List<String> getVariants(String targetUrl) throws IOException;
    Long titleSubscribe(AnimeToSubscribe animeToSubscribe) throws IOException, UnirestException;
    List<AnimeDto> fetchSubscribeList();
    Long titleUnsubscribe(Long id);
}
