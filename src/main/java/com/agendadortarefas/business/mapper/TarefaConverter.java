package com.agendadortarefas.business.mapper;

import com.agendadortarefas.business.dto.TarefaDTO;
import com.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
    TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO);

    TarefaDTO paraTarefaDTO(TarefaEntity tarefaEntity);
}
