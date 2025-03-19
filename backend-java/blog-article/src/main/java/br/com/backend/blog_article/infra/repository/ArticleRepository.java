package br.com.backend.blog_article.infra.repository;

import br.com.backend.blog_article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>{
}
