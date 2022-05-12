package com.codegym.module6_be.service.activity;


import com.codegym.module6_be.model.ActivityLog;
import com.codegym.module6_be.service.GeneralService;

public interface ActivityLogService extends GeneralService<ActivityLog> {
    Iterable<ActivityLog> findByBoardId (Long boardId);
}
