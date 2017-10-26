package org.itglance.docsea.repository;

import org.itglance.docsea.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by soni on 5/8/2017.
 */
public interface StatusRepository extends JpaRepository<Status,Long> {
    public Status findByStatus(String status);
}
