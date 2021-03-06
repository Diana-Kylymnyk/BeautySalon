package org.example.controller;

import org.example.entity.Master;
import org.example.entity.User;
import org.example.service.CommentService;
import org.example.service.MasterService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final MasterService masterService;

    public CommentController(CommentService commentService, MasterService masterService) {
        this.commentService = commentService;
        this.masterService = masterService;
    }

    @GetMapping("/user/comment")
    public String getMasters(@RequestParam Long masterId,
                             @RequestParam String comment,
                             @AuthenticationPrincipal User user) {

        Optional<Master> master = masterService.findById(masterId);
        if (master.isPresent()) {
            commentService.createComment(comment, master.get(), user);
            return "redirect:/user/master/" + masterId+"?success";
        }
        return "redirect:/user/master/"+ masterId+"?error";
    }
}
