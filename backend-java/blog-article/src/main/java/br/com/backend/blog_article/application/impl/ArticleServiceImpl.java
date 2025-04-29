package br.com.backend.blog_article.application.impl;

import br.com.backend.blog_article.application.ArticleService;
import br.com.backend.blog_article.domain.Article;
import br.com.backend.blog_article.infra.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public Article getArticleById(String id){
        return articleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Artigo com id: "+id+" não encontrado"));
    }

}
