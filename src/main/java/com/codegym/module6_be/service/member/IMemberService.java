package com.codegym.module6_be.service.member;


import com.codegym.module6_be.model.DetailedMember;
import com.codegym.module6_be.model.Member;
import com.codegym.module6_be.service.GeneralService;

public interface IMemberService extends GeneralService<Member> {
    Iterable<DetailedMember> getMembersByBoardId(Long boardId);
    Iterable<Member> saveAll(Iterable<Member> members);
    void deleteByBoardIdAndUserId(Long boardId, Long userId);
}
