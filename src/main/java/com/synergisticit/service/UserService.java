package com.synergisticit.service;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findByUsername(String username);

    List<User> findAll();

    User findById(Long id);

    void delete(Long id);
}
