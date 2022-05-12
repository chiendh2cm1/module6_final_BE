package com.codegym.module6_be.repository;

import com.codegym.module6_be.model.Board;
import com.codegym.module6_be.model.SimpleBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.User;


public interface BoardRepository extends PagingAndSortingRepository<Board,Long> {
    Iterable<Board> findAllByOwner(User owner);
    Iterable<Board> findByOwnerId(Long id);

    @Query(value = "select b.id, b.title, u.username as owner from board b join user u on b.owner_id = u.id " +
            "where b.owner_id = ?1 order by b.id desc", nativeQuery = true)
    Iterable<SimpleBoard> findAllOwnedBoardsByUserId(Long userId);

    @Query(value = "select b.id, b.title, u.username as owner from board b join user u on b.owner_id = u.id " +
            "where (b.id in (select m.board_id from member m where m.user_id = ?1)) " +
            "and (b.owner_id <> ?1) order by b.id desc", nativeQuery = true)
    Iterable<SimpleBoard> findAllSharedBoardsByUserId(Long userId);

    @Query(value = "select * from board b " +
            "join board_columns bc on b.id = bc.board_id " +
            "join columns c on bc.columns_id = c.id " +
            "join columns_cards cc on cc.column_id = c.id " +
            "join card cr on cc.cards_id = cr.id  " +
            "where ((b.owner_id = ?1) or (b.id in (select m.board_id from member m where m.user_id = ?1)))" +
            " and ((cr.content like ?2) or cr.title like ?2)", nativeQuery = true)
    Page<Board> findAllAvailableToSearcher(Long searcherId,String keyword, Pageable pageable);

    Iterable<Board> findByType (String type);
    Iterable<Board> findByTypeAndAndOwnerId (String type, Long id);
}
