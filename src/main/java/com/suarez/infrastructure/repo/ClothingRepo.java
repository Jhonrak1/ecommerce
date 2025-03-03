package com.suarez.infrastructure.repo;

import com.suarez.domain.model.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothingRepo extends JpaRepository<Clothing, Long> {
    List<Clothing> findBySize(String size);
    List<Clothing> findByColor(String color);
}
