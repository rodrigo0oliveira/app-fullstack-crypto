package br.com.backend.blog_article.application;

import br.com.backend.blog_article.domain.Article;

public interface ArticleService {

    Article getArticleById(String id);
}
