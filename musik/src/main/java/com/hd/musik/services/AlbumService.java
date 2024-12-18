package com.hd.musik.services;

import com.hd.musik.entity.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbum();

    List<Album> getAlbumByKw(String kw);

    Album getAlbumById(int id);
}
