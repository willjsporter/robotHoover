package com.willjsporter.repository;

import com.willjsporter.model.HooverOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HooverOutputRepository extends JpaRepository<HooverOutput, Long> {

}
