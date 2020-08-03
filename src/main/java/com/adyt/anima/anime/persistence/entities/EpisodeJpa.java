package com.adyt.anima.anime.persistence.entities;

import com.adyt.anima.security.user.UserJpa;
import com.adyt.anima.shared.persistence.StringListConverter;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Accessors(chain = true, fluent = true)
@Table(name = "episodes")
public class EpisodeJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id")
    private AnimeJpa anime;

    private String name;

    private Boolean released;

    private Integer length;

    private Boolean finished;

    @Convert(converter = StringListConverter.class)
    @Column(name = "links", columnDefinition = "clob")
    private List<String> links = new ArrayList<>();

    @Column(name = "last_release_check")
    private Instant lastReleaseCheck;

    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by")
    private UserJpa addedBy;

}
