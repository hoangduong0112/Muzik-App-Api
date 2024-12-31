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
@Table(name = "album", schema = "muzik_db")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonIgnore
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "url", length = 200)
    private String url;

    @OneToMany(mappedBy = "album")
    private List<Song> songs = new ArrayList<>();

    @Transient
    private int countSong;

    @Transient
    private String artistName;

    @JsonProperty("countSong")
    private int getCountSong(){
        return this.songs.size();
    }

    @JsonProperty("artistName")
    private String getArtistName(){
        return this.artist.getName();
    }
}