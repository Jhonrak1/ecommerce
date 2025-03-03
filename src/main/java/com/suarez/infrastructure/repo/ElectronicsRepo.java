package com.suarez.infrastructure.repo;

import com.suarez.domain.model.Electronics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectronicsRepo extends JpaRepository<Electronics, Long> {
    List<Electronics> findByBrand(String brand);
}
