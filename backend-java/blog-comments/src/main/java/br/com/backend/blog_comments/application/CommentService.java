package br.com.backend.blog_comments.application;

import br.com.backend.blog_comments.application.exceptions.CommentNotFoundException;
import br.com.backend.blog_comments.infra.repository.CommentRepository;
import br.com.backend.blog_comments.model.Comment;
import br.com.backend.blog_comments.model.CommentDto;
import br.com.backend.blog_comments.model.CommentRequired;
import br.com.backend.blog_comments.model.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(CommentRequired commentRequired) {
        Comment savedComment = Comment.builder()
                .createdAt(LocalDateTime.now())
                .userName(commentRequired.userName())
                .content(commentRequired.content())
                .idArticle(commentRequired.idArticle())
                .build();

        commentRepository.save(savedComment);

        return new CommentResponseDto("Comentário criado",savedComment.getId());
    }

    public Set<CommentDto> findCommentByArticleId(Long articleId) {
        Set<CommentDto> comments = commentRepository.findCommentsByIdArticle(articleId);
        if(comments.isEmpty()) {
            throw new CommentNotFoundException("Comments not found");
        }
        return comments;
    }
}
