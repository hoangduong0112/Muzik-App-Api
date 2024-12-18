package com.hd.musik.services.servicesImpl;

import com.hd.musik.entity.Album;
import com.hd.musik.exceptions.ResourceNotFoundException;
import com.hd.musik.repository.AlbumRepository;
import com.hd.musik.services.AlbumService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Override
    public List<Album> getAllAlbum() {
        return this.albumRepository.findAll();
    }

    @Override
    public List<Album> getAlbumByKw(String kw) {
        return albumRepository.findByNameContainsIgnoreCase(kw);
    }

    @Override
    public Album getAlbumById(int id) {
        return this.albumRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException((String.format("Album ID: %s does not exist", id)))
        );
    }
}
