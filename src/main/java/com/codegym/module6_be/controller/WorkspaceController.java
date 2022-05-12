package com.codegym.module6_be.controller;

import com.codegym.module6_be.model.Workspace;
import com.codegym.module6_be.service.workspaces.WorkspaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {
    @Autowired
    private WorkspaceServiceImpl workspaceService;

    @GetMapping("")
    public ResponseEntity<Iterable<Workspace>> findAll(){
        return new ResponseEntity<>(workspaceService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/owner/{id}")
    public ResponseEntity<Iterable<Workspace>> findAllByOwnerId(@PathVariable Long id) {
        Iterable<Workspace> workspaces = workspaceService.findAllByOwnerId(id);
        if (workspaces == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(workspaceService.findAllByOwnerId(id), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Workspace> save(@RequestBody Workspace workspaces) {
        return new ResponseEntity<>(workspaceService.save(workspaces), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Workspace>> findById(@PathVariable Long id){
        Optional<Workspace> workspacesOptional = workspaceService.findById(id);
        if (!workspacesOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(workspacesOptional, HttpStatus.OK);
    }

    @GetMapping("/{id}/boards")
    public ResponseEntity<Long> findWorkspaceByBoard(@PathVariable Long id){
        Long workspaceId = workspaceService.getWorkspaceByBoard(id);
        return new ResponseEntity<>(workspaceId, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Workspace> updateById(@PathVariable Long id, @RequestBody Workspace workspace){
        Optional<Workspace> workspacesOptional = workspaceService.findById(id);
        if (!workspacesOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        workspace.setId(workspacesOptional.get().getId());
        workspaceService.save(workspace);
        return new ResponseEntity<>(workspace,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Workspace> deleteById(@PathVariable Long id){
        Optional<Workspace> workspacesOptional = workspaceService.findById(id);
        if (!workspacesOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        workspaceService.deleteById(workspacesOptional.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
