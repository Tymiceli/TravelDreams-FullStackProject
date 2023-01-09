package com.traveldreams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldreams.entity.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository <CountryEntity, Long> {

}
