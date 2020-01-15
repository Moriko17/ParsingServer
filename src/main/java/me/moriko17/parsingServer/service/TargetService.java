package me.moriko17.parsingServer.service;

import java.io.IOException;
import java.util.List;

public interface TargetService {
    int getItemsCount(String targetUrl, String targetPlayer, String targetVoice) throws IOException;
    List<String> getVariants() throws IOException;
}
