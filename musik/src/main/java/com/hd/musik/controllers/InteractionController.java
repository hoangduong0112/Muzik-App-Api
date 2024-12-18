package com.hd.musik.controllers;

import com.hd.musik.entity.Comment;
import com.hd.musik.entity.Song;
import com.hd.musik.entity.User;
import com.hd.musik.services.CommentService;
import com.hd.musik.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/song")
@CrossOrigin
public class InteractionController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeService likeService;

    @GetMapping("/liked")
    public ResponseEntity<?> getLikeUsersOnSong(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ResponseEntity<>(likeService.findAllLikedSongsByUser(user), HttpStatus.OK);
    }
    @PutMapping("/{id}/like")
    public ResponseEntity<?> toggleLikedSongById(@PathVariable int id, Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        Song song = likeService.toggleLikedSong(id, user);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @GetMapping("/{id}/comment")
    public ResponseEntity<?> getCommentsBySong(@PathVariable int id) {

        return new ResponseEntity<>(commentService.getCommentsBySong(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<?> createCommentOnSong(@PathVariable int id, @RequestBody Comment rq,
                                                 Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        Comment comment = commentService.addComment(id, rq, user);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PatchMapping("/comment/{id}")
    public ResponseEntity<?> updateCommentById(@PathVariable int id, @RequestBody Comment rq,
                                               Authentication authentication) {


        User user = (User) authentication.getPrincipal();
        Comment comment = commentService.updateComment(id, rq, user);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable int id, Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        commentService.deleteComment(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}