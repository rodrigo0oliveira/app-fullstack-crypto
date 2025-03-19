package br.com.backend.blog_article.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Article {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private String linkImg;
}
