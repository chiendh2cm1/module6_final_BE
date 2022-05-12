package com.codegym.module6_be.service.workspaces;


import com.codegym.module6_be.model.Workspace;
import com.codegym.module6_be.service.GeneralService;

public interface WorkspaceService extends GeneralService<Workspace> {
    Iterable<Workspace>  findAllByOwnerId (Long id);
    Boolean isBoardInWorkspace(Long boardId);
    Long getWorkspaceByBoard(Long boardId);
}
