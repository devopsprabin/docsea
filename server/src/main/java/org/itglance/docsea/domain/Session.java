package org.itglance.docsea.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by soni on 5/23/2017.
 */
@Entity
public class Session {
    @Id
    private String token;
    private Long hospitalId;
    private Long userId;
    private int userType;
    private String username;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Session{" +
                "token='" + token + '\'' +
                ", hospitalId=" + hospitalId +
                ", userId=" + userId +
                ", userType=" + userType +
                ", username='" + username + '\'' +
                '}';
    }

    public String toStringForToken() {
        return token+"."+hospitalId+"."+userId+"."+userType+"."+username;
    }
}
