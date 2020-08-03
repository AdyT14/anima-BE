package com.adyt.anima.security.user;

import com.adyt.anima.anime.persistence.entities.AnimeJpa;
import com.adyt.anima.anime.persistence.entities.EpisodeJpa;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@Accessors(chain = true, fluent = true)
@Table(name = "users")
public class UserJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "This is not a valid email")
    private String email;

    @Size(min = 8)
    private String password;

    @Size(min = 2, max = 64)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "users_anime",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "anime_id")
    )
    private Set<AnimeJpa> userAnime;

    @ManyToMany
    @JoinTable(
            name = "users_episodes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "episode_id")
    )
    private Set<EpisodeJpa> userEpisodes;

}
