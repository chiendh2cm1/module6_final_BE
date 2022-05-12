package com.codegym.module6_be.service.board;

import com.codegym.module6_be.model.Board;
import com.codegym.module6_be.model.Card;
import com.codegym.module6_be.model.Column;
import com.codegym.module6_be.model.SimpleBoard;
import com.codegym.module6_be.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardRepository iBoardRepository;

    @Override
    public Board findByIdSort(Long id) {
        Board board = iBoardRepository.findById(id).get();
        for (Column column : board.getColumns()) {
            for (Card card : column.getCards()) {
                Collections.sort(card.getTags());
            }
            Collections.sort(column.getCards());
        }
        Collections.sort(board.getColumns());
        Collections.sort(board.getTags());
        return board;
    }

    @Override
    public Iterable<Board> findAllByOwner(User owner) {
        return iBoardRepository.findAllByOwner(owner);
    }
    @Override
    public Iterable<Board> findByOwner(Long id) {
        return iBoardRepository.findByOwnerId(id);
    }

    @Override
    public Iterable<SimpleBoard> findAllOwnedBoardsByUserId(Long userId) {
        return iBoardRepository.findAllOwnedBoardsByUserId(userId);
    }

    @Override
    public Iterable<SimpleBoard> findAllSharedBoardsByUserId(Long userId) {
        return iBoardRepository.findAllSharedBoardsByUserId(userId);
    }

    @Override
    public Page<Board> findAllAvailableToSearcher(Long searcherId, String keyword, Pageable pageable) {
        return iBoardRepository.findAllAvailableToSearcher(searcherId, '%' + keyword + '%', pageable);
    }

    @Override
    public Iterable<Board> findAllBoardByType(String type) {
        return iBoardRepository.findByType(type);
    }

    @Override
    public Iterable<Board> findAllPrivateOwned(String type, Long id) {
        return iBoardRepository.findByTypeAndAndOwnerId(type, id);
    }

    @Override
    public Iterable<Board> findAll() {
        return iBoardRepository.findAll();
    }

    @Override
    public Optional<Board> findById(Long id) {
        return iBoardRepository.findById(id);
    }

    @Override
    public Board save(Board board) {
        return iBoardRepository.save(board);
    }

    @Override
    public void deleteById(Long id) {
        iBoardRepository.deleteById(id);
    }
}




