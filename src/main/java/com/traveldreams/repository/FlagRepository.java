package com.traveldreams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldreams.dto.Flag;

@Repository
public interface FlagRepository extends JpaRepository<Flag, Long>{

}
