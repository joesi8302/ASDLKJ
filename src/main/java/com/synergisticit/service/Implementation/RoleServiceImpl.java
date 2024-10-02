package com.synergisticit.service.Implementation;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;
import com.synergisticit.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;


    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {

        Optional<Role> optionalRole = roleRepository.findById(id);
        optionalRole.ifPresent(role -> roleRepository.delete(role));

    }
}
