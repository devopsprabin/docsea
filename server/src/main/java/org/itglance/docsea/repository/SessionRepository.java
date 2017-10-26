package org.itglance.docsea.repository;

import org.itglance.docsea.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sanj__000 on 5/23/2017.
 */


public interface SessionRepository extends JpaRepository <Session, String>{
    public Session findByToken(String token);
}
