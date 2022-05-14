package com.codegym.module6_be.repository;

import com.codegym.module6_be.model.MemberWorkspace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberWorkspaceRepository extends PagingAndSortingRepository<MemberWorkspace, Long> {
    @Query(nativeQuery = true, value = "select mw.* " +
            "from member_workspace as mw " +
            "         join user u on u.id = mw.user_id " +
            "        join workspace_members wm on mw.id = wm.members_id " +
            "join workspace w on w.id = wm.workspace_id " +
            "where (u.username like ?1 or u.nickname like ?1) and w.id =?2")
    Iterable<MemberWorkspace> findByKeyword(String keyword, Long workspaceId);

    @Query(nativeQuery = true, value = "select * " +
            "from member_workspace as mw " +
            "join user u on u.id = mw.user_id " +
            "join workspace_members wm on mw.id = wm.members_id " +
            "join workspace w on w.id = wm.workspace_id where w.id=?1")
    Page<MemberWorkspace> findAllByWorkspace(Long workspaceId, Pageable pageable);

}

