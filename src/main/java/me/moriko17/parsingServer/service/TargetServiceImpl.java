package me.moriko17.parsingServer.service;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TargetServiceImpl implements TargetService {
    @Override
    public int getItemsCount(String targetUrl, String targetPlayer, String targetVoice) throws IOException {
        String[] lines = Jsoup.connect(targetUrl).get().html().split("\n");

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
    public List<String> getVariants() throws IOException {
        String targetUrl = "https://yummyanime.club/catalog/item/kod-gias-vosstavshij-lelush-r2";
        List<String> variants = new ArrayList<>();

        String[] lines = Jsoup.connect(targetUrl).get().html().split("\n");

        for (String line : lines) {
            if (line.toLowerCase().contains("плеер") && line.toLowerCase().contains("озвучка")) {
                String playerVar = line.substring(line.indexOf("П"), line.indexOf("."));
                String voiceVar = line.substring(line.indexOf("О"));
                variants.add(playerVar + " " + voiceVar);
            }
        }

        return variants;
    }
}
