package com.hd.musik.services;

import com.hd.musik.entity.Playlist;
import com.hd.musik.entity.User;

import java.util.List;

public interface PlaylistService {
    List<Playlist> getPlaylistsByUser(User user, String kw);

    Playlist getPlaylistById(int id, User user);

    Playlist createPlaylist(Playlist playlist, User user);

    Playlist updatePlaylist(int playlistId, Playlist playlist, User user);

    void deletePlaylist(int id, User user);

    Playlist addSongToPlaylist(int playlistId, int songId, User user);

    Playlist removeSongFromPlaylist(int playlistId, int songId, User user);
}
