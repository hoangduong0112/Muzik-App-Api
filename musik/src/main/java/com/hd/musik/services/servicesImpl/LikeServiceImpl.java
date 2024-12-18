package com.hd.musik.services.servicesImpl;

import com.hd.musik.entity.Song;
import com.hd.musik.entity.User;
import com.hd.musik.exceptions.ResourceNotFoundException;
import com.hd.musik.repository.UserRepository;
import com.hd.musik.services.LikeService;
import com.hd.musik.services.SongService;
import com.hd.musik.services.UserService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LikeServiceImpl implements LikeService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongService songService;

    @Override
    public List<Song> findAllLikedSongsByUser(User user) {

        return user.getLikeSongs();
    }

    @Override
    public Song toggleLikedSong(int songId, User user) {

        User managedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Song song = songService.getSongById(songId);
        if (managedUser.getLikeSongs().contains(song)) {
            managedUser.getLikeSongs().remove(song);
        } else {
            managedUser.getLikeSongs().add(song);
        }

        userRepository.save(managedUser);
        return song;
    }
}
