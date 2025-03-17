package br.com.backend.blog_article.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

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
    private String id;

    private String title;

    private String description;

    private LocalDate creationDate;
}
