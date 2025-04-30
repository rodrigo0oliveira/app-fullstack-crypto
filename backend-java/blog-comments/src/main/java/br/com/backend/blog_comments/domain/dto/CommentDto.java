package br.com.backend.blog_comments.domain.dto;

import java.time.LocalDateTime;

public record CommentDto (String content, String userName, LocalDateTime createdAt){
}
