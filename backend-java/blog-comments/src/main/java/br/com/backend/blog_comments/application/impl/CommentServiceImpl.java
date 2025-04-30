package br.com.backend.blog_comments.application.impl;

import br.com.backend.blog_comments.application.CommentService;
import br.com.backend.blog_comments.application.exceptions.CommentNotFoundException;
import br.com.backend.blog_comments.infra.repository.CommentRepository;
import br.com.backend.blog_comments.domain.Comment;
import br.com.backend.blog_comments.domain.dto.CommentDto;
import br.com.backend.blog_comments.domain.dto.CommentRequired;
import br.com.backend.blog_comments.domain.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

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
