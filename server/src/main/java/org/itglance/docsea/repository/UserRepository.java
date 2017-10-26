package org.itglance.docsea.repository;

import org.itglance.docsea.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by soni on 5/8/2017.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);

    public List<User> findAllByUsername(String username);

    @Query("Select u FROM User u WHERE u.username = :username And u.password = :password")
    public User findByUsernameAndPassword(@Param("username") String username , @Param("password") String password);
}
