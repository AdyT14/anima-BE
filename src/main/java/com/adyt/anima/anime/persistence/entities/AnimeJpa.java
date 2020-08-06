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
@Table(name = "anime")
public class AnimeJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    private Boolean finished;

    @Convert(converter = StringListConverter.class)
    @Column(name = "site_urls", columnDefinition = "clob")
    private List<String> siteUrls = new ArrayList<>();

    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by")
    private UserJpa addedBy;

}
