package com.hd.musik.entity;

import com.hd.musik.annotations.JacksonIdSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "artist", schema = "muzik_db")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "photo_url", length = 200)
    private String photoUrl;

    @OneToMany(mappedBy = "artist")
    @JacksonIdSerializer
    private List<Album> albums = new ArrayList<>();

    @OneToMany(mappedBy = "artist")
    @JacksonIdSerializer
    private List<Song> songs = new ArrayList<>();

}