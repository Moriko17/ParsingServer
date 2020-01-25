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
        this.title = animeEntity.getTitle();
        this.imgSource = animeEntity.getImgSource();
        this.viewedCounter = animeEntity.getViewedCounter();
        this.itemsCounter = animeEntity.getItemsCounter();
    }

    private Long id;
    private String targetUrl;
    private String targetPlayer;
    private String targetVoice;
    private String title;
    private String imgSource;
    private int viewedCounter;
    private int itemsCounter;
}
