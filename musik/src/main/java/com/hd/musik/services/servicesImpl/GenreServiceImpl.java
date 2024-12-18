package com.hd.musik.services.servicesImpl;

import com.hd.musik.entity.Genre;
import com.hd.musik.exceptions.ResourceNotFoundException;
import com.hd.musik.repository.GenreRepository;
import com.hd.musik.services.GenreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;
    @Override
    public Genre getGenreById(int id) {
        return this.genreRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Genre ID: %s does not exist", id)));
    }

    @Override
    public List<Genre> getAllGenres() {
        return this.genreRepository.findAll();
    }
}
