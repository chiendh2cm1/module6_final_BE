package com.codegym.module6_be.service.column;


import com.codegym.module6_be.model.Column;
import com.codegym.module6_be.service.GeneralService;

public interface ColumnService extends GeneralService<Column> {
    Iterable<Column> saveAll(Iterable<Column> columns);
}
