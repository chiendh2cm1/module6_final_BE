package com.codegym.module6_be.service.card;

import com.codegym.module6_be.model.Card;
import com.codegym.module6_be.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CardServiceImpl implements CardService{
    @Autowired
    CardRepository cardRepository;
    @Override
    public Iterable<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> findById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void deleteById(Long id) {
cardRepository.deleteById(id);
    }

    @Override
    public Iterable<Card> saveAll(Iterable<Card> cards) {
        return cardRepository.saveAll(cards);
    }

    public Page<Card> findAllAvailableToSearcher(Long searcherId, Pageable pageable){
        return cardRepository.findAllAvailableToSearcher(searcherId, pageable);
    }
}
