package com.hd.musik.services.servicesImpl;

import com.hd.musik.entity.Comment;
import com.hd.musik.entity.Song;
import com.hd.musik.entity.User;
import com.hd.musik.exceptions.ResourceNotFoundException;
import com.hd.musik.exceptions.UnauthorizedException;
import com.hd.musik.repository.CommentRepository;
import com.hd.musik.repository.UserRepository;
import com.hd.musik.services.CommentService;
import com.hd.musik.services.SongService;
import com.hd.musik.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private SongService songService;
    @Autowired
    private UserRepository userRepository;
    private Comment getComment(int id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Comment ID: %s does not exist", id)));
    }
    @Override
    public List<Comment> getCommentsBySong(int songId) {
        Song song = songService.getSongById(songId);
        return song.getComments();
    }

    @Override
    public Comment addComment(int songId, Comment comment, User user) {
        Song song = songService.getSongById(songId);

        Comment rs = new Comment();
        rs.setContent(comment.getContent());
        rs.setUser(user);
        rs.setSong(song);

        return commentRepository.save(rs);
    }

    @Override
    public Comment updateComment(int commentId, Comment comment, User user) {
        Comment oldComment = this.getComment(commentId);
        if (!oldComment.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException(String.format("User: %s cannot update this comment", user.getUsername()));
        }

        oldComment.setContent(comment.getContent());
        return commentRepository.save(oldComment);
    }

    @Override
    public void deleteComment(int commentId, User user) {
        Comment comment = this.getComment(commentId);
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException(String.format("User: %s cannot delete this comment", user.getUsername()));
        }

        commentRepository.delete(comment);
    }

}
