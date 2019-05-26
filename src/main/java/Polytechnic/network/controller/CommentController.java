package Polytechnic.network.controller;

import Polytechnic.network.domain.Comment;
import Polytechnic.network.domain.User;
import Polytechnic.network.domain.Views;
import Polytechnic.network.service.CommentService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;



    public CommentController(CommentService commentService) {
        this.commentService = commentService;

    }

    @PostMapping
    @JsonView(Views.FullComment.class)
    public Comment create(
            @RequestBody Comment comment,
            @AuthenticationPrincipal User user
            ){
        return commentService.create(comment, user);
    }
}
