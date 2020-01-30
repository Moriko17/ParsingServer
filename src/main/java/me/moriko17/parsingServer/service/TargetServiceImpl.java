package me.moriko17.parsingServer.service;

import me.moriko17.parsingServer.domain.SubscribeEntity;
import me.moriko17.parsingServer.models.SubscribeDto;
import me.moriko17.parsingServer.models.AnimeToSubscribe;
import me.moriko17.parsingServer.models.ParseResultDto;
import me.moriko17.parsingServer.repository.SubscribeRepository;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TargetServiceImpl implements TargetService {
    private String yummyRoot = "https://yummyanime.club/catalog/item/";
    private SubscribeRepository subscribeRepository;
    @Autowired
    public TargetServiceImpl(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }

    @Override
    public Long subscribe(AnimeToSubscribe animeToSubscribe) throws IOException {
        ParseResultDto parseResultDto = parse(animeToSubscribe.getTargetUrl(),
                animeToSubscribe.getTargetPlayer(),
                animeToSubscribe.getTargetVoice());
        SubscribeEntity subscribeEntity = new SubscribeEntity(animeToSubscribe,
                parseResultDto.getTitle(),
                parseResultDto.getImgSource(),
                parseResultDto.getItemsCounter());
        subscribeRepository.save(subscribeEntity);

        return subscribeEntity.getId();
    }

    @Override
    public Long unsubscribe(Long id) {
        subscribeRepository.deleteById(id);

        return id;
    }

    @Override
    public List<SubscribeDto> getSubscribes() {
        List<SubscribeDto> subscribes = new ArrayList<>();
        subscribeRepository.findAll().forEach(subscribeEntity -> subscribes.add(new SubscribeDto(subscribeEntity)));

        return subscribes;
    }

    private ParseResultDto parse(String targetUrl, String targetPlayer, String targetVoice) throws IOException {
        String[] lines = Jsoup.connect(yummyRoot + targetUrl).get().html().split("\n");

        int startLine = -1;
        String title = "";
        String imgSource = "";
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].toLowerCase().contains(targetPlayer.toLowerCase())
                    && lines[i].toLowerCase().contains(targetVoice.toLowerCase())) {
                startLine = i;
            }
            if (lines[i].toLowerCase().contains("<h1>")) {
                title = lines[i].substring(lines[i].indexOf(">") + 2, lines[i].lastIndexOf("<") - 1);
            }
            if (lines[i].toLowerCase().contains("poster-block")) {
                int rightString = i+1;
                while (!lines[rightString].contains("img")) {
                    rightString++;
                }
                imgSource = lines[rightString].substring(lines[rightString].indexOf("/"),
                        lines[rightString].lastIndexOf("g") + 1);
            }
        }

        if (startLine == -1) throw new NullPointerException("No such combination");

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

        return new ParseResultDto(targetUrl, targetPlayer, targetVoice, title, imgSource, itemCounter);
    }
}
