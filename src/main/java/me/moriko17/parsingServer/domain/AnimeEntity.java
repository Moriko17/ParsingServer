package me.moriko17.parsingServer.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Anime")
public class AnimeEntity {
    public AnimeEntity(@NotNull @NotBlank String targetUrl,
                       @NotNull @NotBlank String targetPlayer,
                       @NotNull @NotBlank String targetVoice) {
        this.targetUrl = targetUrl;
        this.targetPlayer = targetPlayer;
        this.targetVoice = targetVoice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

//    @NotNull
//    @NotBlank
//    private String title;

    @NotNull
    @NotBlank
    private String targetUrl;

    @NotNull
    @NotBlank
    private String targetPlayer;

    @NotNull
    @NotBlank
    private String targetVoice;
}
