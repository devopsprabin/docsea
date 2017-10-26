package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.Status;
import org.itglance.docsea.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soni on 5/8/2017.
 */
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Status status;
    private int userType;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String password, Status status, int userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.userType = userType;
    }

    public UserDTO(User user){
        this(user.getId(),user.getUsername(),user.getPassword(),user.getStatus(),user.getUserType());
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Status getStatus() {
        return status;
    }

    public int getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", userType=" + userType +
                '}';
    }
}
