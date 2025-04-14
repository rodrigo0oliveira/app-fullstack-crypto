package br.com.backend.blog_security.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.Email;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @NotBlank
    @Column(unique = true,nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_users_roles",
    joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {


        return "{" +
                "\"id\":\"" + id+ "\"," +
                "\"userName\":\"" + username + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"roles\":" + roles.toString()+
                "}";
    }
}
