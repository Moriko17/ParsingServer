package me.moriko17.parsingServer.service;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TargetServiceImpl implements TargetService {
    @Override
    public int getItemsCount(String url) throws IOException {
        String targetUrl = "https://yummyanime.club/catalog/item/igra-na-vyzhivanie";
        String targetPlayer = "kodik";
        String targetVoice = "anilibria";

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
}
