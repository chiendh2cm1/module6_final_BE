package com.codegym.module6_be.service.memberworkspace;

import com.codegym.module6_be.model.MemberWorkspace;
import com.codegym.module6_be.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberWorkspaceService extends GeneralService<MemberWorkspace> {
    Iterable<MemberWorkspace> findByKeyword(String keyword, Long workspaceId);

    Page<MemberWorkspace> findByWorkspace(Long workspaceId, Pageable pageable);
}
