package com.codegym.module6_be.repository;

import com.codegym.module6_be.model.Column;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<Column,Long> {
}
