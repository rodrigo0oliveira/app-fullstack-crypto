package br.com.backend.blog_security.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

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

    @NotBlank
    @Column(unique = true,nullable = false )
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
