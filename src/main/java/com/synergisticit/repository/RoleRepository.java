package com.synergisticit.repository;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(nativeQuery = true,value = "SELECT MAX(role_Id) FROM role")
    Long getMaxId();
}
