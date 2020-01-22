package me.moriko17.parsingServer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.moriko17.parsingServer.domain.AnimeEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnimeDto {
    public AnimeDto(AnimeEntity animeEntity) {
        this.id = animeEntity.getId();
        this.targetUrl = animeEntity.getTargetUrl();
        this.targetPlayer = animeEntity.getTargetPlayer();
        this.targetVoice = animeEntity.getTargetVoice();
    }

    private Long id;
    private String targetUrl;
    private String targetPlayer;
    private String targetVoice;
}
