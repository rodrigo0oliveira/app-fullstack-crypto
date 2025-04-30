package br.com.backend.blog_comments.application;

import br.com.backend.blog_comments.domain.dto.CommentDto;
import br.com.backend.blog_comments.domain.dto.CommentRequired;
import br.com.backend.blog_comments.domain.dto.CommentResponseDto;

import java.util.Set;


public interface CommentService {

    CommentResponseDto createComment(CommentRequired commentRequired);

    Set<CommentDto> findCommentByArticleId(Long articleId);
}
