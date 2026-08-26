package com.agendadortarefas.business.mapper;

import com.agendadortarefas.business.dto.TarefaDTO;
import com.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    @Mapping(source = "dataEvento", target = "dataEvento")
    TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO);

    TarefaDTO paraTarefaDTO(TarefaEntity tarefaEntity);

    List<TarefaEntity> paraListTarefaEntity(List<TarefaDTO> tarefaDTO);

    List<TarefaDTO> paraListTarefaDTO(List<TarefaEntity> tarefaEntity);
}
