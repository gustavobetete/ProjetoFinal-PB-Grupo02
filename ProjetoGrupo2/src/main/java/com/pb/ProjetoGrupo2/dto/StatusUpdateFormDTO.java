package com.pb.ProjetoGrupo2.dto;

import com.pb.ProjetoGrupo2.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusUpdateFormDTO {

    private Status status;

}
