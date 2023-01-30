package com.traveldreams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldreams.entity.Authorities;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

}
