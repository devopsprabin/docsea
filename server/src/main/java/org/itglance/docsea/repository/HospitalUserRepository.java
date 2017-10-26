package org.itglance.docsea.repository;

import org.itglance.docsea.domain.Hospital;
import org.itglance.docsea.domain.HospitalUser;
import org.itglance.docsea.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sanj__000 on 5/11/2017.
 */
public interface HospitalUserRepository extends JpaRepository<HospitalUser,Long> {
    public HospitalUser findByUser(User user);

    public HospitalUser findByHospital(Hospital hospital);
}
