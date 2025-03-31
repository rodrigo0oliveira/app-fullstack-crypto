package br.com.backend.blog_article.application;

import br.com.backend.blog_article.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping(params = "id")
    public ResponseEntity<Article> getArticleById(@RequestParam String id){
        Article article = articleService.getArticleById(id);
        return ResponseEntity.ok().body(article);
    }
}
