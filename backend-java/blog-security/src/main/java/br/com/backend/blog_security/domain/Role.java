package br.com.backend.blog_security.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\", \"name\":\"" + name + "\"}";
    }
}
