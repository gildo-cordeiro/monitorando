package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.repository.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        List<GrantedAuthority> listGrantAuthority = new ArrayList<>();
        var user = userRepository.findByUsername(username);
        checkGrantAuthorities(user, listGrantAuthority);
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), listGrantAuthority);
    }

    private void checkGrantAuthorities(User user, List<GrantedAuthority> listGrantAuthority) {
        if(user != null && user.getAuthorities() != null) {
            for (UserType userType : UserType.values()) {
                if (user.getUserType().equals(userType))
                    listGrantAuthority.add(new SimpleGrantedAuthority(user.getUserType().getCode()));
            }
        }
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}