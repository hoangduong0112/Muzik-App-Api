package com.hd.musik.services.servicesImpl;

import com.hd.musik.entity.Playlist;
import com.hd.musik.entity.Song;
import com.hd.musik.entity.User;
import com.hd.musik.exceptions.ResourceNotFoundException;
import com.hd.musik.exceptions.UnauthorizedException;
import com.hd.musik.repository.PlaylistRepository;
import com.hd.musik.repository.SongRepository;
import com.hd.musik.repository.UserRepository;
import com.hd.musik.services.PlaylistService;
import com.hd.musik.services.SongService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SongService songService;
    @Override
    public List<Playlist> getPlaylistsByUser(User user, String kw) {
        User u = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User %s is not found ", user.getUsername())));

        List<Playlist> playlists = u.getPlaylists();
        if(kw != null)
            playlists.stream().filter(playlist -> playlist.getName().toLowerCase().contains(kw.toLowerCase())).toList();

        return playlists;
    }

    @Override
    public Playlist getPlaylistById(int id, User user) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Playlist ID: %s does not exist", id)));

        if (!playlist.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException(String.format("User: %s is not authorized to access playlist ID: %s",
                    user.getUsername(), id));
        }

        return playlist;
    }

    @Override
    public Playlist createPlaylist(Playlist playlist, User user) {
        User u = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User %s is not found ", user.getUsername())));

        playlist.setUser(u);
        return this.playlistRepository.save(playlist);
    }

    @Override
    public Playlist updatePlaylist(int playlistId, Playlist playlist, User user) {
        Playlist old = this.getPlaylistById(playlistId, user);
        old.setName(playlist.getName());
        return this.playlistRepository.save(old);

    }

    @Override
    public void deletePlaylist(int id, User user) {
        Playlist old = this.getPlaylistById(id, user);
        this.playlistRepository.delete(old);
    }

    @Override
    public Playlist addSongToPlaylist(int playlistId, int songId, User user) {
        User u = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User %s is not found ", user.getUsername())));

        Playlist playlist = this.getPlaylistById(playlistId, user);
        if (!playlist.getUser().getId().equals(u.getId())) {
            throw new UnauthorizedException("You don't have permission to modify this playlist");
        }
        Song song = this.songService.getSongById(songId);
        if (playlist.getSongs().contains(song)) {
            throw new IllegalStateException(String.format("Song ID: %d already exists in the playlist", songId));
        }

        playlist.getSongs().add(song);
        playlistRepository.save(playlist);

        return playlist;
    }

    @Override
    public Playlist removeSongFromPlaylist(int playlistId, int songId, User user) {
        User u = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User %s is not found ", user.getUsername())));

        Playlist playlist = this.getPlaylistById(playlistId, user);
        if (!playlist.getUser().getId().equals(u.getId())) {
            throw new UnauthorizedException("You don't have permission to modify this playlist");
        }
        Song song = this.songService.getSongById(songId);
        if (!playlist.getSongs().contains(song)) {
            throw new IllegalStateException(String.format("Song ID: %d does not exist in the playlist", songId));
        }
        playlist.getSongs().remove(song);
        playlistRepository.save(playlist);

        return playlist;
    }
}
