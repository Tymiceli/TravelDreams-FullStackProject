package com.traveldreams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.traveldreams.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long> {

	@Query ("select u from UserEntity u "
			+ "left join fetch u.authorities "
			+ "where u.username = :username")
	UserEntity findByUsername(String username);

}
