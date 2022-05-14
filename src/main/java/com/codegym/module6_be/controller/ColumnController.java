package com.codegym.module6_be.controller;

import com.codegym.module6_be.model.Column;
import com.codegym.module6_be.service.column.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/columns")
@CrossOrigin("*")
public class ColumnController {
    @Autowired
    ColumnService columnService;

    @GetMapping
    public ResponseEntity<Iterable<Column>> findAll() {
        Iterable<Column> columnIterable = columnService.findAll();
        return new ResponseEntity<>(columnIterable, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Column> findById(@PathVariable Long id) {
        Optional<Column> optionalColumn = columnService.findById(id);
        if (!optionalColumn.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(optionalColumn.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Column> save(@RequestBody Column column) {
        Column column1 = columnService.save(column);
        return new ResponseEntity<>(column1, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Iterable<Column>> saveAll(@RequestBody Iterable<Column> columns) {
        Iterable<Column> column = columnService.saveAll(columns);
        return new ResponseEntity<>(column, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Column> update(@PathVariable Long id, @RequestBody Column column) {
        Optional<Column> optionalColumn = columnService.findById(id);
        if (!optionalColumn.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            column.setId(id);
            return new ResponseEntity<>(columnService.save(column), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Column> deleteById(@PathVariable Long id) {
        Optional<Column> optionalColumn = columnService.findById(id);
        if (!optionalColumn.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            columnService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
