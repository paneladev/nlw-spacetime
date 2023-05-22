package com.pdev.spacetime.repository;

import com.pdev.spacetime.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
}
