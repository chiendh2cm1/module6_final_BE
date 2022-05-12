package com.codegym.module6_be.controller;

import com.codegym.module6_be.model.Board;
import com.codegym.module6_be.model.DetailedMember;
import com.codegym.module6_be.model.Member;
import com.codegym.module6_be.model.MemberWorkspace;
import com.codegym.module6_be.service.board.BoardService;
import com.codegym.module6_be.service.member.IMemberService;
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
@CrossOrigin
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private IMemberService memberService;

    @GetMapping
    public ResponseEntity<Iterable<Board>> findAll() {
        Iterable<Board> boardIterable = boardService.findAll();
        return new ResponseEntity<>(boardIterable, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Iterable<Board>> findAllByType(@PathVariable("type") String type){
        Iterable<Board> boardIterable = boardService.findAllBoardByType(type);
        return new ResponseEntity<>(boardIterable,HttpStatus.OK);
    }
        @GetMapping("/owned/{type}/{id}")
    public ResponseEntity<Iterable<Board>> findAllByTypeAndUser(@PathVariable("type") String type,@PathVariable Long id){
        Iterable<Board> boardIterable = boardService.findAllPrivateOwned(type,id);
        return new ResponseEntity<>(boardIterable,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> findById(@PathVariable Long id) {
        Optional<Board> boardOptional = boardService.findById(id);
        if (!boardOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(boardOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/sort/{id}")
    public ResponseEntity<Board> findByIdSorted(@PathVariable Long id) {
        return new ResponseEntity<>(boardService.findByIdSort(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Board> add(@RequestBody Board board) {
        Board savedBoard = boardService.save(board);
        Member member = new Member(null, savedBoard.getOwner(), true, savedBoard);
        Member savedMember = memberService.save(member);
        System.out.println(savedMember);
        return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id,@RequestBody Board board) {
        Board updatedBoard = boardService.save(board);
        return new ResponseEntity<>(updatedBoard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Board> deleteById(@PathVariable Long id) {
        Optional<Board> boardOptional = boardService.findById(id);
        if (!boardOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/available-to/{searcherId}/{keyword}")
    public ResponseEntity<Map<String, Object>> findAllAvailableToSearcher(@PathVariable Long searcherId,
                                                                      @PathVariable String keyword,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page,size);
        Page<Board> boardPage = boardService.findAllAvailableToSearcher(searcherId,keyword,paging);
        List<Board> boardPageContent = boardPage.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("boards",boardPageContent);
        response.put("currentPage",boardPage.getNumber());
        response.put("totalItems",boardPage.getTotalElements());
        response.put("totalPages",boardPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{userId}/owned-boards")
    public ResponseEntity<Iterable<Board>> findAllOwnedBoardsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(boardService.findByOwner(userId), HttpStatus.OK);
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<Iterable<DetailedMember>> findMembersByBoardId(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.getMembersByBoardId(id), HttpStatus.OK);
    }


//    @Autowired
//    private WorkspaceService workspaceService;
//    @GetMapping("/{id}/is-in-workspace")
//    public ResponseEntity<Boolean> isBoardInWorkspace(@PathVariable Long id) {
//        return new ResponseEntity<>(workspaceService.isBoardInWorkspace(id), HttpStatus.OK);
//    }
    @PostMapping("/delete")
    public ResponseEntity<MemberWorkspace> deleteAllById(@RequestBody Iterable<Board> boards) {
        for (Board board : boards) {
            boardService.deleteById(board.getId());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
