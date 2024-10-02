package com.synergisticit.service.Implementation;

import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;
import com.synergisticit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        if(!user.getUserPassword().startsWith("$2a$")){
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    public void updateUser(User user){

    }

    @Override
    public void delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.ifPresent(User -> userRepository.delete(optionalUser.get()));
    }
}
