package br.com.backend.blog_comments.application;

import br.com.backend.blog_comments.domain.dto.CommentDto;
import br.com.backend.blog_comments.domain.dto.CommentRequired;
import br.com.backend.blog_comments.domain.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody @Valid CommentRequired commentRequired) {
        CommentResponseDto commentDto = commentService.createComment(commentRequired);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
    }

    @GetMapping
    public ResponseEntity<Set<CommentDto>> getAllCommentByArticleId(@RequestParam Long articleId) {
        Set<CommentDto> comments = commentService.findCommentByArticleId(articleId);
        return ResponseEntity.ok(comments);
    }
}
