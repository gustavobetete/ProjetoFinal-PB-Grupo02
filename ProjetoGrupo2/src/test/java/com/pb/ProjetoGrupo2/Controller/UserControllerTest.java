//package com.pb.ProjetoGrupo2.Controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.pb.ProjetoGrupo2.builder.UserBuilder;
//import com.pb.ProjetoGrupo2.dto.UserDto;
//import com.pb.ProjetoGrupo2.dto.UserFormDto;
//import com.pb.ProjetoGrupo2.entities.User;
//import com.pb.ProjetoGrupo2.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private ModelMapper modelMapper;
//
//    @Test
//    void postUser() throws Exception{
//
//        User user = UserBuilder.getUser();
//        UserDto userDto = UserBuilder.getUserDto();
//
//       when(userService.save(any())).thenReturn(userDto);
//
//       mockMvc.perform(post("/users")
//               .contentType(MediaType.APPLICATION_JSON)
//               .accept(MediaType.APPLICATION_JSON)
//               .content(objectMapper.writeValueAsString(user)))
//               .andExpect(status().isCreated())
//               .andExpect(jsonPath("$.name").value(user.getName()))
//               .andDo(print());
//    }
//
//    @Test
//    void getUsers() throws Exception{
//
//        List<UserDto> userDtoList = new ArrayList<>(
//                Arrays.asList(UserBuilder.getUserDto(), UserBuilder.getUserDtoTwo())
//        );
//
//        PageRequest pageRequest = PageRequest.of(0, 5);
//
//        Page<UserDto> userDtoPage =
//                new PageImpl<>(userDtoList, pageRequest, userDtoList.size());
//
//        when(userService.findAll(any(PageRequest.class))).thenReturn(userDtoPage);
//
//        mockMvc.perform(get("/users")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content.[0].name").value("Aluno"))
//                .andExpect(jsonPath("$.content.[1].name").value("Adm"))
//                .andDo(print());
//
//    }
//
//    @Test
//    void getUserById() throws Exception{
//
//        User user = UserBuilder.getUser();
//        UserDto userDto = UserBuilder.getUserDto();
//
//        when(userService.findById(user.getId())).thenReturn(userDto);
//
//        long id = 1;
//
//        mockMvc.perform(get("/users/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value(userDto.getName()))
//                .andDo(print());
//    }
//
//    @Test
//    void updateUser() throws Exception{
//
//        User user = UserBuilder.getUser();
//        UserFormDto userFormDto = UserBuilder.getUserFormDto();
//        UserDto userDto = UserBuilder.getUserDto();
//
//        user.setName("Novo usuário");
//        userFormDto.setName("Novo usuário");
//        userDto.setName("Novo usuário");
//
//        when(userService.update(anyLong(), any(UserFormDto.class))).thenReturn(userDto);
//
//        mockMvc.perform(put("/users/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(userFormDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value(userFormDto.getName()))
//                .andDo(print());
//    }


//    @Test
//    void deleteUser() throws Exception{
//
//        User user = UserBuilder.getUser();
//
//        when(userService.deleteById(user.getId())).thenReturn(ResponseEntity.ok().build());
//        mockMvc.perform(delete("/users/1")).andExpect(status().isOk()).andDo(print());
//
//    }


