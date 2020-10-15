package com.warehouse.demo.repository;

import com.warehouse.demo.model.Piano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PianoRepository extends JpaRepository<Piano, Long> {

    @Query(value = "SELECT * FROM piano AS p WHERE p.name=:name", nativeQuery = true)
    Piano getPianoByName(@Param("name") String name);

    @Query(value = "SELECT * FROM piano As p WHERE p.model_of_piano=:modelOfPiano", nativeQuery = true)
    List<Piano> getAllPianoByModel(@Param("modelOfPiano") String model);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE piano AS p SET p.price=:price  WHERE p.sku=:sku", nativeQuery = true)
    Integer updatePianoPriceWithSku(@Param("sku") String sku, @Param("price") Integer price);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM piano WHERE sku=:sku", nativeQuery = true)
    Integer deletePianoWithSku(@Param("sku") String sku);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM piano WHERE piano_id=:id", nativeQuery = true)
    Integer deletePianoWithId(@Param("id") Long id);

    @Query(value = "SELECT * FROM piano AS p WHERE p.sku=:sku", nativeQuery = true)
    Piano getPianoBySKU(@Param("sku") String sku);

    @Query(value = "SELECT * FROM piano AS p WHERE p.piano_id=:id", nativeQuery = true)
    Piano getPianoById(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE piano AS p SET p.borrowed=:borrowed WHERE p.sku=:sku", nativeQuery = true)
    Piano updatePianoBorrowedWithSku(@Param("sku") String SKU, @Param("borrowed") Boolean borrowed);

    @Query(value = "SELECT * FROM piano AS p WHERE p.sku=:sku AND p.borrowed != 1",nativeQuery = true)
    Piano getPianoBySkuWhichIsNotBorrowed(@Param("sku") String sku);

}
