package com.codegym.module6_be.controller;

import com.codegym.module6_be.model.Card;
import com.codegym.module6_be.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
@CrossOrigin("*")
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping
    public ResponseEntity<Iterable<Card>> findAll() {
        return new ResponseEntity<>(cardService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById(@PathVariable Long id) {
        Optional<Card> optionalColumn = cardService.findById(id);
        if (!optionalColumn.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(optionalColumn.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody Card card) {
        return new ResponseEntity<>(cardService.save(card), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Iterable<Card>> saveAll(@RequestBody Iterable<Card> cards) {
        return new ResponseEntity<>(cardService.saveAll(cards), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> update(@PathVariable Long id, @RequestBody Card card) {
        Optional<Card> optionalCard = cardService.findById(id);
        if (!optionalCard.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            card.setId(id);
            return new ResponseEntity<>(cardService.save(card), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Card> deleteById(@PathVariable Long id) {
        Optional<Card> cardOptional = cardService.findById(id);
        if (!cardOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            cardService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/available-to/{searcherId}")
    public ResponseEntity<Map<String, Object>> findAllAvailableToSearcher(@PathVariable Long searcherId,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page,size);
        Page<Card> boardPage = cardService.findAllAvailableToSearcher(searcherId,paging);
        List<Card> boardPageContent = boardPage.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("boards",boardPageContent);
        response.put("currentPage",boardPage.getNumber());
        response.put("totalItems",boardPage.getTotalElements());
        response.put("totalPages",boardPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
