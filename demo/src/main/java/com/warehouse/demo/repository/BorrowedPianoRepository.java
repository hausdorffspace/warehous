package com.warehouse.demo.repository;

import com.warehouse.demo.model.BorrowedPiano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedPianoRepository extends JpaRepository<BorrowedPiano,Long> {
}
