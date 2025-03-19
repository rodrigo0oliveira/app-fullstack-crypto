package br.com.backend.blog_article.application;

import br.com.backend.blog_article.domain.Article;
import br.com.backend.blog_article.infra.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id){
        return articleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Artigo com id: "+id+" não encontrado"));
    }

}
