package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserService userServiceImpl;

    public AuthenticationService(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException {
        List<GrantedAuthority> listGrantAuthority = new ArrayList<>();
        var user = userServiceImpl.findByEmail(login);
        checkGrantAuthorities(user, listGrantAuthority);
        return new org.springframework.security.core.userdetails.User(login, user.getPass(), listGrantAuthority);
    }

    private void checkGrantAuthorities(User user, List<GrantedAuthority> listGrantAuthority) {
        if(user != null && user.getUserType() != null) {
            for (UserType userType : UserType.values()) {
                if (user.getUserType().equals(userType))
                    listGrantAuthority.add(new SimpleGrantedAuthority(user.getUserType().getCode()));
            }
        }
    }
}