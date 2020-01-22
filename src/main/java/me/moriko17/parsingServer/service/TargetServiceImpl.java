package me.moriko17.parsingServer.service;

import me.moriko17.parsingServer.domain.AnimeEntity;
import me.moriko17.parsingServer.models.AnimeDto;
import me.moriko17.parsingServer.models.AnimeToSubscribe;
import me.moriko17.parsingServer.repository.AnimeRepository;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TargetServiceImpl implements TargetService {
    private String yummyRoot = "https://yummyanime.club/catalog/item/";
    private AnimeRepository animeRepository;
    @Autowired
    public TargetServiceImpl(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @Override
    public int getItemsCount(String targetUrl, String targetPlayer, String targetVoice) throws IOException {
        String[] lines = Jsoup.connect(yummyRoot + targetUrl).get().html().split("\n");

        int startLine = -1;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].toLowerCase().contains(targetPlayer) && lines[i].toLowerCase().contains(targetVoice)) {
                startLine = i;
                break;
            }
        }

        if (startLine == -1) return -1;
        int currentLineNumber = startLine + 5;
        int openDivCount = 0;
        int itemCounter = 0;
        while (true) {
            if (lines[currentLineNumber].contains("<div")) openDivCount++;
            if (lines[currentLineNumber].contains("</div>")) openDivCount--;
            itemCounter++;
            currentLineNumber++;
            if (openDivCount == 0) {
                itemCounter = (itemCounter - 1) / 3;
                break;
            }
        }

        return itemCounter;
    }

    @Override
    public List<String> getVariants(String targetUrl) throws IOException {
        List<String> variants = new ArrayList<>();

        String[] lines = Jsoup.connect(yummyRoot + targetUrl).get().html().split("\n");

        for (String line : lines) {
            if (line.toLowerCase().contains("плеер") && line.toLowerCase().contains("озвучка")) {
                String playerVar = line.substring(line.indexOf("П"), line.indexOf("."));
                String voiceVar = line.substring(line.indexOf("О"));
                variants.add(playerVar + " " + voiceVar);
            }
        }

        return variants;
    }

    @Override
    public Long titleSubscribe(AnimeToSubscribe animeToSubscribe) {
        AnimeEntity animeEntity = new AnimeEntity(animeToSubscribe.getTargetUrl(),
                animeToSubscribe.getTargetPlayer(),
                animeToSubscribe.getTargetVoice());
        animeRepository.save(animeEntity);

        return animeEntity.getId();
    }

    @Override
    public List<AnimeDto> fetchSubscribeList() {
        List<AnimeDto> subscribes = new ArrayList<>();
        animeRepository.findAll().forEach(animeEntity -> subscribes.add(new AnimeDto(animeEntity)));

        return subscribes;
    }
}
