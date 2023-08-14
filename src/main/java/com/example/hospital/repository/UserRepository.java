package com.example.hospital.repository;

import com.example.hospital.entity.RoleEntity;
import com.example.hospital.entity.Status;
import com.example.hospital.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin (String login);
    UserEntity findByEmail (String email);
    List<UserEntity> findAllByActive(Status status);
    UserEntity findByCodeActivities(String codeActivities);

    List<UserEntity> findAllByRoleEntityList (RoleEntity role);



}
