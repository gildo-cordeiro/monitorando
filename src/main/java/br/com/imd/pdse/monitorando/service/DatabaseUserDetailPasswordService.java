//package br.com.imd.pdse.monitorando.service;
//
//import br.com.imd.pdse.monitorando.domain.User;
//import br.com.imd.pdse.monitorando.domain.enums.UserType;
//import br.com.imd.pdse.monitorando.repository.UserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsPasswordService;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class DatabaseUserDetailPasswordService implements UserDetailsPasswordService {
//
//    private final UserRepository userRepository;
//
//    public DatabaseUserDetailPasswordService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails updatePassword(UserDetails user, String newPassword) {
//        List<GrantedAuthority> listGrantAuthority = new ArrayList<>();
//        var userCredentials = userRepository.findByEmail(user.getUsername());
//        userCredentials.setPass(newPassword);
//        checkGrantAuthorities(userCredentials, listGrantAuthority);
//        return new org.springframework.security.core.userdetails.User(userCredentials.getLogin(), user.getPass(), listGrantAuthority);
//    }
//
//    private void checkGrantAuthorities(User user, List<GrantedAuthority> listGrantAuthority) {
//        if(user != null && user.getUserType() != null) {
//            for (UserType userType : UserType.values()) {
//                if (user.getUserType().equals(userType))
//                    listGrantAuthority.add(new SimpleGrantedAuthority(user.getUserType().getCode()));
//            }
//        }
//    }
//}
