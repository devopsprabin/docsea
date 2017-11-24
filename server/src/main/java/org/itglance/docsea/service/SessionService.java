package org.itglance.docsea.service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.itglance.docsea.domain.HospitalUser;
import org.itglance.docsea.domain.Session;
import org.itglance.docsea.domain.User;

import org.itglance.docsea.repository.HospitalRepository;
import org.itglance.docsea.repository.HospitalUserRepository;
import org.itglance.docsea.repository.SessionRepository;
import org.itglance.docsea.repository.StatusRepository;
import org.itglance.docsea.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sanj__000 on 5/23/2017.
 */

@Service
@Transactional
public class    SessionService {
    public static Map<String, Session> sessions = new ConcurrentHashMap<>();
    private final SessionRepository sessionRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    private final HospitalUserRepository hospitalUserRepository;

    public SessionService(SessionRepository sessionRepository, HospitalRepository hospitalRepository, UserRepository userRepository, StatusRepository statusRepository, HospitalUserRepository hospitalUserRepository) {
        this.sessionRepository = sessionRepository;
        this.hospitalRepository = hospitalRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.hospitalUserRepository = hospitalUserRepository;
    }

    public Session checkSession(String authenticate) {
        //TODO DO Base64 Decode
     /*   byte[] decodedBytes = Base64.getDecoder().decode(authenticate.getBytes());
        String str = new String(decodedBytes);
        System.out.println("Token = "+str);
        String[]  array = str.split("\\.");
        System.out.println("After spliting token:");
        for(String s : array){
            System.out.println(s);
        }

        String token = null;
        Long hospitalId = null;
        Long userId = null;
        int userType ;
        try {
           token = array[0];
           hospitalId = Long.valueOf(array[1]);
           userId = Long.valueOf(array[2]);
           userType = Integer.parseInt(array[3]);
        }catch(Exception e)
        {
            System.out.println("This exception came from arrey ignore it");
        }*/
        String token = authenticate;
        if(sessions.containsKey(token))
        {
           return sessions.get(token);
        }else{
            Session  session = sessionRepository.findByToken(token);
            if(session != null){
                sessions.put(token, session);
                return session;
            }
        }
        return null;
    }

    public Session createSession(User dbUser){
        HospitalUser hospitalUser =hospitalUserRepository.findByUser(dbUser);
        String token = UUID.randomUUID().toString();
        Session session = new Session();
        session.setToken(token);
        if(hospitalUser != null){
            session.setHospitalId(hospitalUser.getHospital().getId());

        }
        session.setUserId(dbUser.getId());
        session.setUserType(dbUser.getUserType());
        session.setUsername(dbUser.getUsername());
        sessions.put(token, session);
        sessionRepository.save(session);
        return session;
    }

    public void removeSession(String token){
       /* byte[] decodedBytes = Base64.getDecoder().decode(authenticate.getBytes());
        String str = new String(decodedBytes);
        String[] array = str.split("\\.");
        String token = array[0];*/
            sessions.remove(token);
            System.out.println("Session deleted sucessfull !!!!");
    }

}
