package br.com.backend.blog_article.application;

import br.com.backend.blog_article.domain.Article;

import java.util.List;

public interface ArticleService {

    Article getArticleById(String id);

    List<Article> getAllArticles();
}
