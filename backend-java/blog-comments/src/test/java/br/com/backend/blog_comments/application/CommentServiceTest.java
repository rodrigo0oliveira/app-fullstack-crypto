package br.com.backend.blog_comments.application;

import br.com.backend.blog_comments.application.exceptions.CommentNotFoundException;
import br.com.backend.blog_comments.application.impl.CommentServiceImpl;
import br.com.backend.blog_comments.infra.repository.CommentRepository;
import br.com.backend.blog_comments.domain.Comment;
import br.com.backend.blog_comments.domain.dto.CommentDto;
import br.com.backend.blog_comments.domain.dto.CommentRequired;
import br.com.backend.blog_comments.domain.dto.CommentResponseDto;
import org.junit.jupiter.api.Assertions;;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentServiceImpl commentService;

    LocalDateTime dateTime = LocalDateTime.now();

    @Test
    void testCreateCommentWhenCommentIsOkShouldReturnCommentCreated(){
        Comment comment = Comment.builder()
                .id(1L)
                .createdAt(dateTime)
                .idArticle("1")
                .userName("rodrigo")
                .content("comment here")
                .build();

        CommentRequired commentRequired =
                new CommentRequired("comment here","1","rodrigo");

        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentResponseDto actualResponse = commentService.createComment(commentRequired);

        Assertions.assertEquals("Comentário criado", actualResponse.message());
        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void testFindCommentsByArticleIdWhenCommentExistShouldReturnSetComments(){

        CommentDto comment = new CommentDto("comment 1 here",
                "rodrigo",
                LocalDateTime.now());

        CommentDto comment2 = new CommentDto("comment 2 here",
                "rodrigo",
                LocalDateTime.now());

        Set<CommentDto> expectedComments = Set.of(comment, comment2);

        when(commentRepository.findCommentsByIdArticle(1L)).thenReturn(expectedComments);

        Set<CommentDto> actualComments = commentRepository.findCommentsByIdArticle(1L);

        Assertions.assertEquals(expectedComments, actualComments);
        verify(commentRepository).findCommentsByIdArticle(1L);
    }

    @Test
    void testFindCommentsByArticleIdWhenCommentNotExistShouldThrowException(){
        when(commentRepository.findCommentsByIdArticle(1L)).thenReturn(Set.of());

        Assertions.assertThrows(CommentNotFoundException.class,
                () -> commentService.findCommentByArticleId(1L));
        verify(commentRepository).findCommentsByIdArticle(1L);
    }
}
