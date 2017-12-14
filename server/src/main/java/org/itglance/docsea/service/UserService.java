package org.itglance.docsea.service;

import org.itglance.docsea.config.CryptoUtil;
import org.itglance.docsea.domain.Session;
import org.itglance.docsea.domain.User;
import org.itglance.docsea.repository.HospitalUserRepository;
import org.itglance.docsea.repository.UserRepository;
import org.itglance.docsea.service.dto.SessionDTO;
import org.itglance.docsea.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by soni on 5/8/2017.
 */
@Service
@Transactional
public class UserService {


    @Autowired
    SessionService sessionService;

    private final HospitalUserRepository hospitalUserRepository;
    private final UserRepository userRepository;

    public UserService(HospitalUserRepository hospitalUserRepository, UserRepository userRepository) {
        this.hospitalUserRepository = hospitalUserRepository;
        this.userRepository = userRepository;
    }

    public SessionDTO validateLogin(UserDTO userDTO){
        User dbUser = null;
        try {
            System.out.println("------- checking password and username-------");
            dbUser = userRepository.findByUsernameAndPassword(userDTO.getUsername(), CryptoUtil.encrypt(userDTO.getPassword(), userDTO.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(dbUser != null){
            Session session = sessionService.createSession(dbUser);
           /* String mainToken =  session.toStringForToken();
            byte[] encode = Base64.getEncoder().encode(mainToken.getBytes());*/
            System.out.println("-----password and username matched");
            return new SessionDTO(session);
        }
        System.out.println("-----password and username invalid");
        return null;
    }

    public boolean isUserActive(Long userId) {
        User user = userRepository.findOne(userId);
        if(user.getStatus().getStatus().equalsIgnoreCase("ACTIVE")){
            return true;
        }
        return  false;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
}
