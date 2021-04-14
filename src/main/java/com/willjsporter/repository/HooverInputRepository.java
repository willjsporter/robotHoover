package com.willjsporter.repository;

import com.willjsporter.model.HooverInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HooverInputRepository extends JpaRepository<HooverInput, Long> {

}
