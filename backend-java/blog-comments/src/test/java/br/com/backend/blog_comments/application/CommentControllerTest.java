package br.com.backend.blog_comments.application;

import br.com.backend.blog_comments.application.exceptions.CommentNotFoundException;

import br.com.backend.blog_comments.domain.Comment;
import br.com.backend.blog_comments.domain.dto.CommentDto;
import br.com.backend.blog_comments.domain.dto.CommentRequired;
import br.com.backend.blog_comments.domain.dto.CommentResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentController.class)
public class CommentControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @MockBean
    private final CommentService commentService;

    @Autowired
    public CommentControllerTest(MockMvc mockMvc,
                                 ObjectMapper objectMapper,CommentService commentService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.commentService = commentService;
    }


    @Test
    void testCreateCommentWhenCommentIsOkShouldReturnCreatedStatus() throws Exception {
        Comment comment = Comment.builder()
                .id(1L)
                .createdAt(LocalDateTime.now())
                .idArticle("1")
                .userName("rodrigo")
                .content("comment here")
                .build();

        when(commentService.createComment(any(CommentRequired.class)))
                .thenReturn(any(CommentResponseDto.class));

        ResultActions response = mockMvc.perform(post("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comment)));

        response.andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void testFindCommentsByArticleIdWhenCommentExistShouldReturnOk() throws Exception {

        CommentDto comment = new CommentDto("comment 1 here",
                "rodrigo",
                LocalDateTime.now());

        CommentDto comment2 = new CommentDto("comment 2 here",
                "rodrigo",
                LocalDateTime.now());

        Set<CommentDto> comments = Set.of(comment, comment2);

        when(commentService.findCommentByArticleId(1L)).thenReturn(comments);

        ResultActions response = mockMvc.perform(get("/comments")
                .param("articleId","1"));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(comments)));


    }

    @Test
    void testFindCommentsByArticleIdWhenSetIsEmptyShouldReturnNotFound() throws Exception {
        when(commentService.findCommentByArticleId(1L)).thenThrow(new CommentNotFoundException("Comments not found"));

        ResultActions response = mockMvc.perform(get("/comments")
                .param("articleId","1"));

        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Comments not found"));

    }
}
