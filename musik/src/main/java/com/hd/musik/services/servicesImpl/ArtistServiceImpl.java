package com.hd.musik.services.servicesImpl;

import com.hd.musik.entity.Artist;
import com.hd.musik.exceptions.ResourceNotFoundException;
import com.hd.musik.repository.ArtistRepository;
import com.hd.musik.services.ArtistService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Artist getArtistById(int id) {
        return this.artistRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(String.format("Artist ID: %s does not exist", id)));
    }

    @Override
    public List<Artist> getAllArtists() {
        return this.artistRepository.findAll();
    }

    @Override
    public List<Artist> getArtistsByWord(String kw) {
        return artistRepository.findByNameContainsIgnoreCase(kw);
    }


}
