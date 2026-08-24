package com.agendadortarefas.business;

import com.agendadortarefas.business.dto.TarefaDTO;
import com.agendadortarefas.business.mapper.TarefaConverter;
import com.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTO criaTarefa(String token, TarefaDTO tarefaDTO){
        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefaDTO.setEmailUsuario(email);
        TarefaEntity tarefaEntity = tarefaConverter.paraTarefaEntity(tarefaDTO);
        tarefaEntity = tarefaRepository.save(tarefaEntity);
        return tarefaConverter.paraTarefaDTO(tarefaEntity);
    }
}
