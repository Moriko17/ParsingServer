package me.moriko17.parsingServer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnimeToSubscribe {
    private String targetUrl;
    private String targetPlayer;
    private String targetVoice;
}
