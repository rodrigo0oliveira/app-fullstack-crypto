package br.com.backend.blog_security.application;

import br.com.backend.blog_security.domain.Role;
import org.springframework.stereotype.Service;

public interface RoleService {

    Role saveRole(String name);

    Role findRoleByName(String name);
}
