package com.synergisticit.repository;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(nativeQuery = true,value = "SELECT MAX(user_Id) FROM user")
    Long getMaxId();


}
