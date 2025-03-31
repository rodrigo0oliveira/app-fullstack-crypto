package br.com.backend.blog_article.application;

import br.com.backend.blog_article.domain.Article;
import br.com.backend.blog_article.infra.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @InjectMocks
    private static ArticleService articleService;

    @Mock
    private static ArticleRepository articleRepository;

    @Test
    void testGetArticleByIdWhenArticleExistShouldReturnArticle(){
        Article article1 = Article.builder()
                .id("1")
                .title("ArticleTest1")
                .description("ArticleDescription1")
                .linkImg("http://mockimgtest")
                .build();

        when(articleRepository.findById("1"))
                .thenReturn(Optional.of(article1));

        Article articleTest = articleService.getArticleById("1");

        Assertions.assertEquals(article1,articleTest);
        verify(articleRepository).findById("1");
        verifyNoMoreInteractions(articleRepository);

    }

    @Test
    void testGetArticleByIdWhenArticleNotExistShouldThrowException(){

        String id = "1";
        String errorExpected = "Artigo com id: "+id+" não encontrado";

        when(articleRepository.findById("1"))
                .thenReturn(Optional.empty());

        EntityNotFoundException exception =  Assertions.assertThrows(EntityNotFoundException.class,()->
                articleService.getArticleById("1")
        );

        Assertions.assertEquals(errorExpected,exception.getMessage());
        verify(articleRepository).findById("1");

    }
}
