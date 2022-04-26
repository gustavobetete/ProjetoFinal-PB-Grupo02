//package com.pb.ProjetoGrupo2.services;
//
//import com.pb.ProjetoGrupo2.builder.UserBuilder;
//import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
//import com.pb.ProjetoGrupo2.dto.UserDto;
//import com.pb.ProjetoGrupo2.dto.UserFormDto;
//import com.pb.ProjetoGrupo2.entities.User;
//import com.pb.ProjetoGrupo2.repository.UserRepository;
//import com.pb.ProjetoGrupo2.service.UserServiceImpl;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DisplayName("Tests for user Service")
//public class UserServiceTest {
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    @MockBean
//    private UserRepository repository;
//
//    @Test
//    @DisplayName("Save user")
//    public void saveUser() {
//        User user = UserBuilder.getUser();
//
//        when(this.repository.save(any(User.class))).thenReturn(user);
//
//        UserDto userDTO = this.userService.save(UserBuilder.getUserFormDto());
//
//        assertThat(userDTO.getId()).isNotNull();
//        assertThat(userDTO.getName()).isEqualTo(user.getName());
//        assertThat(userDTO.getEmail()).isEqualTo(user.getEmail());
//
//    }
//
//    @Test
//    @DisplayName("List all user")
//    public void listUser() {
//        User user = UserBuilder.getUser();
//
//        PageRequest pageRequest = PageRequest.of(0, 10);
//        List<User> users = Arrays.asList(user);
//        Page<User> page = new PageImpl<>(users, pageRequest, 1);
//
//        when(this.repository.findAll(any(PageRequest.class))).thenReturn(page);
//
//        Page<UserDto> pageUserDTO = this.userService.findAll(pageRequest);
//
//        assertThat(pageUserDTO.getContent()).hasSize(1);
//        assertThat(pageUserDTO.getTotalPages()).isEqualTo(1);
//        assertThat(pageUserDTO.getTotalElements()).isEqualTo(1);
//    }
//
//    @Test
//    @DisplayName("FindById user")
//    public void findByIdUser() {
//        User user = UserBuilder.getUser();
//
//        when(this.repository.findById(anyLong())).thenReturn(Optional.of(user));
//
//        UserDto userDTO = this.userService.findById(user.getId());
//
//        assertThat(userDTO.getId()).isNotNull();
//        assertThat(userDTO.getName()).isEqualTo(user.getName());
//        assertThat(userDTO.getEmail()).isEqualTo(user.getEmail());
//
//    }
//
//    @Test
//    @DisplayName("findById user not found")
//    public void findByIdUser_NotFound() {
//        User user = UserBuilder.getUser();
//
//        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThatExceptionOfType(ObjectNotFoundException.class)
//                .isThrownBy(() -> this.userService.findById(user.getId()));
//    }
//
//    @Test
//    @DisplayName("Update user")
//    public void updateUser() {
//        User user = UserBuilder.getUser();
//        UserFormDto userFormDTO = UserBuilder.getUserFormDto();
//        userFormDTO.setName("Aluno");
//
//        when(this.repository.findById(anyLong())).thenReturn(Optional.of(user));
//        when(this.repository.save(any(User.class))).thenReturn(user);
//
//        UserDto userDTO = this.userService.update(user.getId(), userFormDTO);
//
//        assertThat(userDTO.getId()).isNotNull();
//        assertThat(userDTO.getName()).isEqualTo(userFormDTO.getName());
//        assertThat(userDTO.getEmail()).isEqualTo(userFormDTO.getEmail());
//
//    }
//
//    @Test
//    @DisplayName("Update user not found")
//    public void updateUser_NotFound() {
//        User user = UserBuilder.getUser();
//
//        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThatExceptionOfType(ObjectNotFoundException.class)
//                .isThrownBy(() -> this.userService.update(user.getId(), UserBuilder.getUserFormDto()));
//    }
//
//    @Test
//    @DisplayName("Delete user")
//    public void deleteUser() {
//        User user = UserBuilder.getUser();
//
//        when(this.repository.findById(anyLong())).thenReturn(Optional.of(user));
//
//        this.userService.deleteById(1L);
//
//        verify(this.repository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    @DisplayName("Delete user not found")
//    public void deleteUser_NotFound() {
//        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThatExceptionOfType(ObjectNotFoundException.class)
//                .isThrownBy(() -> this.userService.deleteById(1L));
//    }
//}
