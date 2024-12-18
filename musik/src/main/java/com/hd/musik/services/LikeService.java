package com.hd.musik.services;

import com.hd.musik.entity.Song;
import com.hd.musik.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface LikeService {
    List<Song> findAllLikedSongsByUser(User user);
    Song toggleLikedSong(int songId, User user);
}
