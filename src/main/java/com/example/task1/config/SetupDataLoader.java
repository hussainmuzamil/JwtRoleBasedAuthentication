package com.example.task1.config;

import com.example.task1.entity.Role;
import com.example.task1.entity.User;
import com.example.task1.repository.RoleRepository;
import com.example.task1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean isSetup = false;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public SetupDataLoader(
            @Autowired  UserRepository userRepository
            ,@Autowired RoleRepository roleRepository
    ){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(isSetup){
            return;
        }else{
            Role adminRole = createRole("ADMIN");
            Role userRole = createRole("User");

            Role adminRl = roleRepository.findByName("ADMIN");
            User user = new User();
            user.setName("Muzammil");
            user.setEmail("abc123@gmail.com");
            user.setRoles(Arrays.asList(adminRl));
            user.setPassword("abc123");
            userRepository.save(user);

            Role userRl = roleRepository.findByName("USER");
            User user2 = new User();
            user2.setName("Muzammil2");
            user2.setEmail("abc234@gmail.com");
            user2.setPassword("abc234");
            user2.setRoles(Arrays.asList(userRl));
            userRepository.save(user2);
            isSetup = true;
        }
    }

    private Role createRole(String name) {
        Role role = roleRepository.findByName(name);
        if(role == null){
            role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
        return role;
    }
}
