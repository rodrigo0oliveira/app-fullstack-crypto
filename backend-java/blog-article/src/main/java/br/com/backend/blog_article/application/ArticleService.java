package br.com.backend.blog_article.application;

import br.com.backend.blog_article.domain.Article;
import br.com.backend.blog_article.infra.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    public Article getArticleById(String id){
        return articleRepository.findById(id).orElse(null);
    }

}
