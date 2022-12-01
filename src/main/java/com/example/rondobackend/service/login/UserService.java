package com.example.rondobackend.service.login;

import com.example.rondobackend.config.SecurityConfiguration;
import com.example.rondobackend.model.login.Engros;
import com.example.rondobackend.model.login.User;
import com.example.rondobackend.repo.EngrosRepository;
import com.example.rondobackend.repo.login.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService implements IUserService {

    private UserRepository userRepository;
    private EngrosRepository engrosRepository;

    @Override
    public Set<User> findAll() {
        Set<User> set = new HashSet<>();
        userRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public User save(User user) {
//        if(user.getPassword() == null) {
            PasswordEncoder pw = SecurityConfiguration.passwordEncoder();
            user.setPassword(pw.encode(user.getPassword()));
//        }
        return userRepository.save(user);
    }

    @Override
    public void delete(User object) {
            userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<User> findById(Long aLong) {
        return userRepository.findById(aLong);
    }

    @Override
    public List<User> findByName(String name) {
        System.out.println("Userservice called findByName with argument: " + name);
        return userRepository.findByUsername(name);
    }

    @Override
    public String findByNameValid(String name) {
        System.out.println("Userservice called findByName with argument: " + name);
        return engrosRepository.findByName(name).get(0).getName();
    }
}