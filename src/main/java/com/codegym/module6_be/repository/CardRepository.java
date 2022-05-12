package com.codegym.module6_be.repository;

import com.codegym.module6_be.model.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Long> {
    @Query(value = "select * from card where column_id in(select column_id from columns where board_id = ?1)",
            nativeQuery = true)
    Iterable<Card> findCardsByBoardId(Long boardId);

    @Query(value = "select * from board b " +
            "join board_columns bc on b.id = bc.board_id " +
            "join columns c on bc.columns_id = c.id " +
            "join columns_cards cc on cc.column_id = c.id " +
            "join card cr on cc.cards_id = cr.id  " +
            "where ((b.owner_id = ?1) or (b.id in (select m.board_id from member m where m.user_id = ?1))) ", nativeQuery = true)
    Page<Card> findAllAvailableToSearcher(Long searcherId, Pageable pageable);
}
