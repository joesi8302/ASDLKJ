package com.synergisticit.service;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    List<Role> findAll();

    Role findById(Long id);

    void delete(Long id);
}
