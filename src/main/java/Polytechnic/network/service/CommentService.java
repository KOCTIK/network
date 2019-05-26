package Polytechnic.network.service;

import Polytechnic.network.domain.Comment;
import Polytechnic.network.domain.User;
import Polytechnic.network.domain.Views;
import Polytechnic.network.dto.EventType;
import Polytechnic.network.dto.ObjectType;
import Polytechnic.network.repo.CommentRepo;
import Polytechnic.network.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user){
        comment.setAuthor(user);
        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);

        return commentFromDb;
    }
}
