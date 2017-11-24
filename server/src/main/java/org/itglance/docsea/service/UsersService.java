package org.itglance.docsea.service;


import org.itglance.docsea.domain.Authority;
import org.itglance.docsea.domain.Users;
import org.itglance.docsea.repository.AuthorityRepository;
import org.itglance.docsea.repository.UsersRepository;
import org.itglance.docsea.service.dto.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UsersService {

    private final Logger log = LoggerFactory.getLogger(UsersService.class);

    private final UsersRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    public UsersService(UsersRepository userRepository,
                        PasswordEncoder passwordEncoder,
                        AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    public Users createUser(UsersDTO usersDTO) {
        Users user = new Users();
        user.setLogin(usersDTO.getLogin());
        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        for (String s : usersDTO.getAuthorities()) {
            authority.setName(s);
        }
        authorities.add(authority);
        user.setAuthorities(authorities);
        String encryptedPassword = passwordEncoder.encode(usersDTO.getPassword());
        user.setPassword(encryptedPassword);
        user.setActivated(true);
        userRepository.save(user);
        log.info("Created Information for Users: {}", user);
        return user;
    }

    public List<Users> findAllUser(){
        return userRepository.findAll();
    }


}
