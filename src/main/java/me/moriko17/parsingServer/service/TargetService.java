package me.moriko17.parsingServer.service;

import java.io.IOException;
import java.util.List;

public interface TargetService {
    int getItemsCount(String url) throws IOException;
    List<String> getVariants() throws IOException;
}
