package com.warehouse.demo.repository;

import com.warehouse.demo.model.Rentier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentierRepository extends JpaRepository<Rentier,Long> {
}
