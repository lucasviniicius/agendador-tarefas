package com.agendadortarefas.business.mapper;

import com.agendadortarefas.business.dto.TarefaDTO;
import com.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {
    void updateTarefa(@MappingTarget  TarefaEntity tarefaEntity, TarefaDTO tarefaDTO);
}
