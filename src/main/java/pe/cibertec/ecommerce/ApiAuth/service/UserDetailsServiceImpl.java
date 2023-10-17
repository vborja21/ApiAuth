
package pe.cibertec.ecommerce.ApiAuth.service;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.cibertec.ecommerce.ApiAuth.dao.UserRepository;
import pe.cibertec.ecommerce.ApiAuth.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) 
            throws UsernameNotFoundException {
        User user= userRepository.findByEmail(email);
        Set<GrantedAuthority> authorities= user.getRoles()
                .stream()
                .map((role)-> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails
            .User(user.getUsername(), 
           user.getPassword(), authorities);
    }
}
