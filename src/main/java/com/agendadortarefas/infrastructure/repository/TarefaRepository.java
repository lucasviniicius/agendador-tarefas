package com.agendadortarefas.infrastructure.repository;

import com.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {

    List<TarefaEntity> findByDataEventoBetweenAndStatusNotificacaoEnum(LocalDateTime dataInicial, LocalDateTime dataFinal, StatusNotificacaoEnum status);

    List<TarefaEntity> findByEmailUsuario(String emailUsuario);
}
