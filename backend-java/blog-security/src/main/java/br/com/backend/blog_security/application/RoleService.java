package br.com.backend.blog_security.application;

import br.com.backend.blog_security.domain.Role;
import br.com.backend.blog_security.infra.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role saveRole(String name){
        Role role = roleRepository.findByName(name);
        if(role==null){
            Role newRole = Role.builder()
                    .name(name).build();

            roleRepository.save(newRole);
            return newRole;
        }
        return role;
    }

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

}
