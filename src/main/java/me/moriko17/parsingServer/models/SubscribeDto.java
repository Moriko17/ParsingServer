package me.moriko17.parsingServer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.moriko17.parsingServer.domain.SubscribeEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubscribeDto {
    public SubscribeDto(SubscribeEntity subscribeEntity) {
        this.id = subscribeEntity.getId();
        this.targetUrl = subscribeEntity.getTargetUrl();
        this.targetPlayer = subscribeEntity.getTargetPlayer();
        this.targetVoice = subscribeEntity.getTargetVoice();
        this.title = subscribeEntity.getTitle();
        this.imgSource = subscribeEntity.getImgSource();
        this.viewedCounter = subscribeEntity.getViewedCounter();
        this.itemsCounter = subscribeEntity.getItemsCounter();
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
