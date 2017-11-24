package org.itglance.docsea.repository;

import org.itglance.docsea.domain.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    @EntityGraph(attributePaths = "authorities")
    Optional<Users> findOneWithAuthoritiesByLogin(String login);

    Optional<Users> findOneByLogin(String login);

}
