package com.example.hospital.repository;

import com.example.hospital.entity.RoleEntity;
import com.example.hospital.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByTitleIgnoreCase(String roleTitle);

    List<RoleEntity> findByUserEntityList (UserEntity entity);
}
