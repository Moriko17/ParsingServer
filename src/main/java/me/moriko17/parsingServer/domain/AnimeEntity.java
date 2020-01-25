package me.moriko17.parsingServer.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.moriko17.parsingServer.models.AnimeToSubscribe;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Anime")
public class AnimeEntity {
    public AnimeEntity(AnimeToSubscribe animeToSubscribe,
                       @NotNull @NotBlank String title,
                       @NotNull @NotBlank String imgSource,
                       @NotNull int itemsCounter) {
        this.targetVoice = animeToSubscribe.getTargetVoice();
        this.targetPlayer = animeToSubscribe.getTargetPlayer();
        this.targetUrl = animeToSubscribe.getTargetUrl();
        this.title = title;
        this.imgSource = imgSource;
        this.itemsCounter = itemsCounter;
        this.viewedCounter = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @NotBlank
    private String targetUrl;

    @NotNull
    @NotBlank
    private String targetPlayer;

    @NotNull
    @NotBlank
    private String targetVoice;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String imgSource;

    @NotNull
    private int viewedCounter;

    @NotNull
    private int itemsCounter;
}
