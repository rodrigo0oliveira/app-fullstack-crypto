package br.com.backend.blog_article.application;

import br.com.backend.blog_article.domain.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles(){
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok().body(articles);
    }

    public ResponseEntity<Article> getArticleById(@RequestBody String id){
        Article article = articleService.getArticleById(id);
        return ResponseEntity.ok().body(article);
    }
}
