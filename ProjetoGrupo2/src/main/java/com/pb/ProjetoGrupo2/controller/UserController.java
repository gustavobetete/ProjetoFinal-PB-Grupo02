package com.pb.ProjetoGrupo2.controller;

import com.pb.ProjetoGrupo2.dto.*;
import com.pb.ProjetoGrupo2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> postUser(@RequestBody @Valid UserFormDTO userFormDto) {
        UserDTO user = userService.postUser(userFormDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping()
    public Page<UserDTO> getAllUsers
            (@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<UserDTO> users = userService.getAllUsers(pageable);
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> putUser
            (@PathVariable Long id,
             @RequestBody @Valid UpdatedUserFormDTO updatedUserFormDTO) {
        UserDTO user = userService.putUser(id, updatedUserFormDTO);
        return ResponseEntity.ok(user);
    }
}
