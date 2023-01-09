package com.traveldreams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.traveldreams.entity.CountryEntity;

@Component
@Repository
public interface CountryRepository extends JpaRepository <CountryEntity, Long> {

}
