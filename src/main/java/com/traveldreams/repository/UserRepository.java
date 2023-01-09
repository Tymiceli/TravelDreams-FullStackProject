package com.traveldreams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldreams.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long> {

}
