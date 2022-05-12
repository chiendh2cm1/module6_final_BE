package com.codegym.module6_be.service.workspaces;

import com.codegym.module6_be.model.Workspace;
import com.codegym.module6_be.repository.IWorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkspaceServiceImpl implements WorkspaceService{
    @Autowired
    private IWorkspaceRepository workspaceRepository;

    @Override
    public Iterable<Workspace> findAll() {
        return workspaceRepository.findAll();
    }

    @Override
    public Optional<Workspace> findById(Long id) {
        return workspaceRepository.findById(id);
    }

    @Override
    public Workspace save(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }

    @Override
    public void deleteById(Long id) {
        workspaceRepository.deleteById(id);
    }

    @Override
    public Iterable<Workspace> findAllByOwnerId(Long id) {
        return workspaceRepository.findAllByOwnerId(id);
    }

    @Override
    public Boolean isBoardInWorkspace(Long boardId) {
        Integer isInWorkspace = workspaceRepository.isBoardInWorkspace(boardId);
        if (isInWorkspace != 0) {
            return true;
        }
        return false;

    }

    @Override
    public Long getWorkspaceByBoard(Long boardId) {
        return workspaceRepository.getWorkspaceByBoard(boardId);
    }
}
