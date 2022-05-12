package com.codegym.module6_be.service.board;

import com.codegym.module6_be.model.Board;
import com.codegym.module6_be.model.SimpleBoard;
import com.codegym.module6_be.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;

public interface BoardService extends GeneralService<Board> {
    Board findByIdSort(Long id);
    Iterable<Board> findAllByOwner(User owner);
    Iterable<Board> findByOwner(Long id);
    Iterable<SimpleBoard> findAllOwnedBoardsByUserId(Long userId);
    Iterable<SimpleBoard> findAllSharedBoardsByUserId(Long userId);
    Page<Board> findAllAvailableToSearcher(Long searcherId, String keyword, Pageable pageable);
    Iterable<Board> findAllBoardByType(String type);
    Iterable<Board> findAllPrivateOwned(String type, Long id);
}
