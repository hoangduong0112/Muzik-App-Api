package com.hd.musik.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hd.musik.annotations.JacksonIdSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "song", schema = "muzik_db")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "song_url", nullable = false, length = 200)
    private String songUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JacksonIdSerializer
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Transient
    private String artistName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JacksonIdSerializer
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "album_id")
    private Album album;

    @OneToMany(mappedBy = "song")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "likeSongs")
    @JsonIgnore
    private List<User> likeUsers = new ArrayList<>();

    @ManyToMany(mappedBy = "songs")
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(mappedBy = "songs")
    @JsonIgnore
    private List<Playlist> playlists = new ArrayList<>();

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int likeCount;

    @JsonProperty("likeCount")
    public int getLikeCount() {
        return this.getLikeUsers().size();
    }

    @JsonProperty("artistName")
    public String getArtistName(){
        return this.getArtist().getName();
    }
}