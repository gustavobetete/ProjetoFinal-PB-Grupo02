package com.pb.ProjetoGrupo2.dto;

import com.pb.ProjetoGrupo2.config.validation.InstitutionalEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFormDto {

    @NotNull(message = "O campo Name não pode ser nulo")
    @NotEmpty(message = "O campo Name não pode ser vazio")
    @NotBlank(message = "O campo Name não pode estar em branco")
    private String name;

    @NotNull(message = "O campo Email não pode ser nulo")
    @NotEmpty(message = "O campo Email não pode ser vazio")
    @NotBlank(message = "O campo Email não pode estar em branco")
    @InstitutionalEmail
    @Email
    private String email;

    @NotNull(message = "O campo Password não pode ser nulo")
    @NotEmpty(message = "O campo Password não pode ser vazio")
    @NotBlank(message = "O campo Password não pode estar em branco")
    private String password;

}
