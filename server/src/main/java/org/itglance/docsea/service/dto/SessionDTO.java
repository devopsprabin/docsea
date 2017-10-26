package org.itglance.docsea.service.dto;

import org.itglance.docsea.domain.Session;
import org.itglance.docsea.domain.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by soni on 5/23/2017.
 */
public class SessionDTO {

    private String token;
    private Long hospitalId;
    private Long userId;
    private int userType;
    private String username;

    public SessionDTO() {
    }

    public SessionDTO(String token, Long hospitalId, Long userId, int userType, String username) {
        this.token = token;
        this.hospitalId = hospitalId;
        this.userId = userId;
        this.userType = userType;
        this.username = username;
    }

    public SessionDTO(Session session) {
        this(session.getToken(), session.getHospitalId(), session.getUserId(),session.getUserType(),session.getUsername());
    }

    public String getToken() {
        return token;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public Long getUserId() {
        return userId;
    }

    public int getUserType() {
        return userType;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "SessionDTO{" +
                "token='" + token + '\'' +
                ", hospitalId=" + hospitalId +
                ", userId=" + userId +
                ", userType=" + userType +
                ", username='" + username + '\'' +
                '}';
    }

    /* private static Map<String, Session> sessions = new ConcurrentHashMap<>();
    public Session checkSession(String auth){
        //TODO DO Base64 Decode
        String token = "";
        String userId = "";
        Integer userType = 2;
        if(sessions.containsKey(token)){
            return sessions.get(token);
        }
        //Get From Database and Add to sessions
        return null;
    }

    public Session createSession(User user){
        String token = UUID.randomUUID().toString();
        //INsert INDB
        //Add to Map
        Session session = new Session();

        sessions.put(token, session);
        return session;
    }

    public void removeSession(String auth){
        sessions.remove(auth);
        //REmove From DB Also
    }*/


}
