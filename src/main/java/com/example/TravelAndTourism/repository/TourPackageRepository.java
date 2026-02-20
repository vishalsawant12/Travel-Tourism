package com.example.TravelAndTourism.repository;

import com.example.TravelAndTourism.models.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TourPackageRepository extends JpaRepository <TourPackage ,Long> {

    Optional<TourPackage> findByName(String name);
}
