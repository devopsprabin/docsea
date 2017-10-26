package org.itglance.docsea.domain;

public class DoctorStatus {


    private Doctor doctor;

    private Status status;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DoctorStatus{" +
                "doctor=" + doctor +
                ", status=" + status +
                '}';
    }
}
