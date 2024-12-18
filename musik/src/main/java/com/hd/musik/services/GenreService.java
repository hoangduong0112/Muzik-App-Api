package com.hd.musik.services;


import com.hd.musik.entity.Genre;

import java.util.List;

public interface GenreService {
    Genre getGenreById(int id);
    List<Genre> getAllGenres();
}
