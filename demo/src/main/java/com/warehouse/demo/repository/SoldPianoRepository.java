package com.warehouse.demo.repository;

import com.warehouse.demo.model.SoldPiano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldPianoRepository extends JpaRepository<SoldPiano,Long> {

}
