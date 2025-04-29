package br.com.backend.blog_comments.domain.dto;

public record CommentRequired(String content,String idArticle,String userName) {
}
