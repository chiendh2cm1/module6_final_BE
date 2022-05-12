package com.codegym.module6_be.service.card;

import com.codegym.module6_be.model.Card;
import com.codegym.module6_be.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService extends GeneralService<Card> {
    Iterable<Card> saveAll(Iterable<Card> cards);
    Page<Card> findAllAvailableToSearcher(Long searcherId, Pageable pageable);
}
