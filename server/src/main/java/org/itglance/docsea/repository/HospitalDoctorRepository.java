package org.itglance.docsea.repository;


import org.itglance.docsea.domain.Doctor;
import org.itglance.docsea.domain.Hospital;
import org.itglance.docsea.domain.HospitalDoctor;
import org.itglance.docsea.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalDoctorRepository extends JpaRepository<HospitalDoctor,Long>{

    @Query("SELECT hd FROM HospitalDoctor  hd WHERE hd.hospital.id=:hospitalId AND hd.doctor.id=:doctorId ")
    public HospitalDoctor findByHospitalDoctor(@Param("hospitalId") Long hospitalId, @Param("doctorId") Long doctorId);

    @Query("SELECT hd.doctor FROM HospitalDoctor hd WHERE " +
            "hd.doctor.name=:searchString " +
            "OR hd.doctor.speciality.name = :searchString " +
            "OR hd.hospital.name = :searchString " +
            "OR hd.hospital.address.streetAddress = :searchString ")
public List<Doctor> findDoctorByString(@Param("searchString") String searchString);


    @Query("SELECT hd.hospital FROM HospitalDoctor hd WHERE hd.doctor = :doctor AND hd.status LIKE:status")
    public List<Hospital> findAllByDoctor(@Param("doctor") Doctor doctor, @Param("status") Status status);



    @Query("SELECT  hd FROM HospitalDoctor hd WHERE hd.doctor = :doctor AND hd.hospital = :hospital")
    public HospitalDoctor findByHospitalAndDoctor(@Param("hospital") Hospital hospital,@Param("doctor") Doctor doctor);


    @Query("SELECT hd.doctor FROM HospitalDoctor hd WHERE hd.hospital = :hospital AND hd.status NOT LIKE:status")
   public List<Doctor> findAllByHospital(@Param("hospital") Hospital hospital, @Param("status") Status status);


    @Query("SELECT hd.status FROM HospitalDoctor hd WHERE hd.hospital = :hospital AND hd.doctor = :doctor")
    public Status findStatusByHospitalDoctor(@Param("hospital") Hospital hospital,@Param("doctor") Doctor doctor);

    @Query("SELECT hd.doctor FROM HospitalDoctor hd WHERE hd.hospital = :hospital")
    public List<Doctor> findAllByHospital(@Param("hospital") Hospital hospital);

    public HospitalDoctor findByDoctor(Doctor doctor);
}

