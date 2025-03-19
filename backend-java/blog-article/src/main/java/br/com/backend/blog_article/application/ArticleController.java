package br.com.backend.blog_article.application;

import br.com.backend.blog_article.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles(){
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Article> getArticleById(@RequestParam Long id){
        Article article = articleService.getArticleById(id);
        return ResponseEntity.ok().body(article);
    }
}
