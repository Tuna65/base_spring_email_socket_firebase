package com.example.base.repository;

import com.example.base.enums.Status;
import com.example.base.models.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  User findByUserNameAndStatus(String userName, Status status);

}
