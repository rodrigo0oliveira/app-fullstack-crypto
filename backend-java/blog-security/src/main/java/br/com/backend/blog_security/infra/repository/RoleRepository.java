package br.com.backend.blog_security.infra.repository;

import br.com.backend.blog_security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    @Query("select r from Role as r where r.name = :name")
    Role findByName(@Param("name") String name);
}
