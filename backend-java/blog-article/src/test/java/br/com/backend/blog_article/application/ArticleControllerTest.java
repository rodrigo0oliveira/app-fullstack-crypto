package br.com.backend.blog_article.application;

import br.com.backend.blog_article.domain.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ArticleController.class)
public class ArticleControllerTest {


    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @MockBean
    private final ArticleService articleServiceMock;

    @Autowired
    public ArticleControllerTest(MockMvc mockMvc,ObjectMapper objectMapper,ArticleService articleServiceMock) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.articleServiceMock = articleServiceMock;
    }

    @Test
    void testGetArticleByIdWhenArticleExistShouldReturnOk() throws Exception {

        Article article1 = Article.builder()
                .id("1")
                .title("ArticleTest1")
                .description("ArticleDescription1")
                .linkImg("http://mockimgtest")
                .build();

        when(this.articleServiceMock.getArticleById("1")).thenReturn(article1);

        ResultActions response = mockMvc.perform(
                get("/article").param("id",article1.getId()));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(article1)));
    }

    @Test
    void testGetArticleByIdWhenArticleNotExistShouldReturnNotFound() throws Exception {
        when(this.articleServiceMock.getArticleById("1")).thenThrow(EntityNotFoundException.class);

        ResultActions response = mockMvc.perform(get("/article")
                .param("id","1"));

        response.andDo(print())
                .andExpect(status().isNotFound());
    }
}
