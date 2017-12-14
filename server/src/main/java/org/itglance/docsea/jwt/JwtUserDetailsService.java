package org.itglance.docsea.jwt;

import org.itglance.docsea.config.CryptoUtil;
import org.itglance.docsea.domain.User;
import org.itglance.docsea.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return new JwtUser(user);
        }
    }

    public JwtUser findByUsernameAndPassword(String user_name, String password) throws Exception {
        User user = userRepository.findByUsername(user_name);
        String encrypted_password = CryptoUtil.encrypt(password, user_name);
        String db_password = user.getPassword();

        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        } else {
            if(encrypted_password.equals(db_password)){
//            String decrypt_password = CryptoUtil.decrypt(user.getPassword(),user.getUsername());
//            if(password.equals(decrypt_password)){
//
                return new JwtUser(user);
            }
            else{
                throw new BadCredentialsException("incorrect password");

            }
        }
    }
}
