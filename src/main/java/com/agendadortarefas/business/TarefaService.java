package com.agendadortarefas.business;

import com.agendadortarefas.business.dto.TarefaDTO;
import com.agendadortarefas.business.mapper.TarefaConverter;
import com.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefaDTO criaTarefa(String token, TarefaDTO tarefaDTO){
        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefaDTO.setEmailUsuario(email);
        TarefaEntity tarefaEntity = tarefaConverter.paraTarefaEntity(tarefaDTO);
        tarefaEntity = tarefaRepository.save(tarefaEntity);
        return tarefaConverter.paraTarefaDTO(tarefaEntity);
    }

    public List<TarefaDTO> buscaTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefaConverter.paraListTarefaDTO(
                tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal)
        );
    }

    public List<TarefaDTO> buscaTarefaPorEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));

        return tarefaConverter.paraListTarefaDTO(
                tarefaRepository.findByEmailUsuario(email)
        );
    }

    public void removeTarefaPorId(String id){
        TarefaEntity tarefaEntity = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não existe."));

        tarefaRepository.deleteById(id);
    }

    public TarefaDTO alteraStatusTarefa(StatusNotificacaoEnum status, String id){
        TarefaEntity tarefaEntity = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não existe."));

        tarefaEntity.setStatusNotificacaoEnum(status);

        return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefaEntity));
    }

    public TarefaDTO alteraTarefa(TarefaDTO tarefaDTO, String id){
        TarefaEntity tarefaEntity = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não existe."));

        tarefaUpdateConverter.updateTarefa(tarefaEntity, tarefaDTO);
        
        return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefaEntity));
    }
}
