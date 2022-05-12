package com.codegym.module6_be.repository;

import com.codegym.module6_be.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    @Query(nativeQuery = true, value = "select * " +
            "from activity_log " +
            "where board_id = ?1 " +
            "order by id desc " +
            "limit 99;")
    Iterable<ActivityLog> findByBoardId (Long boardId);
}
