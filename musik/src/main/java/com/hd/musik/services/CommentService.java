package com.hd.musik.services;

import com.hd.musik.entity.Comment;
import com.hd.musik.entity.User;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsBySong(int songId);
    Comment addComment(int songId, Comment comment, User user);

    Comment updateComment(int commentId, Comment comment, User user);
    void deleteComment(int commentId, User user);
}
