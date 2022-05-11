package com.codegym.module6_be.service.activity;

import com.codegym.module6_be.model.ActivityLog;
import com.codegym.module6_be.repository.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityLogImpl implements ActivityLogService{
    @Autowired
    private ActivityLogRepository activityLogRepository;
    @Override
    public Iterable<ActivityLog> findAll() {
        return activityLogRepository.findAll();
    }

    @Override
    public Optional<ActivityLog> findById(Long id) {
        return activityLogRepository.findById(id);
    }

    @Override
    public ActivityLog save(ActivityLog activityLog) {
        return activityLogRepository.save(activityLog);
    }

    @Override
    public void deleteById(Long id) {
        activityLogRepository.deleteById(id);
    }

    @Override
    public Iterable<ActivityLog> findByBoardId(Long boardId) {
        return activityLogRepository.findByBoardId(boardId);
    }
}
