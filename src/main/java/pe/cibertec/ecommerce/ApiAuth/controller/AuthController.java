
package pe.cibertec.ecommerce.ApiAuth.controller;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.cibertec.ecommerce.ApiAuth.Dto.SignUpDto;
import pe.cibertec.ecommerce.ApiAuth.entity.Role;
import pe.cibertec.ecommerce.ApiAuth.entity.User;
import pe.cibertec.ecommerce.ApiAuth.service.RoleService;
import pe.cibertec.ecommerce.ApiAuth.service.UserService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired 
    private RoleService roleService;
    @Autowired
    private AuthenticationManager authenticarionManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto user){
        User userEntity = new User();
        userEntity.setName(user.getName());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRoles(user.getRoles());
        //Role roles = roleService.findByName("ADMIN");
        //userEntity.setRoles(Collections.singleton(roles));
        var result = userService.add(userEntity);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }   
}
