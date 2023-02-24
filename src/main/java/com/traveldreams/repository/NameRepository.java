package com.traveldreams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldreams.dto.Name;

@Repository
public interface NameRepository extends JpaRepository<Name, Long>{

}
