package com.warehouse.demo.repository;

import com.warehouse.demo.model.Piano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PianoRepository extends JpaRepository<Piano,Long> {

    @Query(value = "SELECT * FROM piano AS p WHERE p.name=:name", nativeQuery = true)
    Piano getPianoByName(@Param("name") String name);

    @Query(value ="SELECT * FROM piano As p WHERE p.model_of_piano=:modelOfPiano",nativeQuery = true)
    List<Piano> getAllPianoByModel(@Param("modelOfPiano") String model);
}
