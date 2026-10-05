package com.agendadortarefas.controller;

import com.agendadortarefas.business.TarefaService;
import com.agendadortarefas.business.dto.TarefaDTO;
import com.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> criaTarefa(@RequestBody TarefaDTO tarefaDTO, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.criaTarefa(token, tarefaDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscaTarefaPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){
        return ResponseEntity.ok(tarefaService.buscaTarefasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscaTarefaPorEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.buscaTarefaPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeTarefaPorId(@RequestParam String id){
        tarefaService.removeTarefaPorId(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDTO> alteraStatusTarefa(@RequestParam("status")StatusNotificacaoEnum status,
                                                        @RequestParam("id") String id){
        return ResponseEntity.ok(tarefaService.alteraStatusTarefa(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> alteraTarefa(@RequestBody TarefaDTO tarefaDTO,
                                                  @RequestParam("id") String id){
        return ResponseEntity.ok(tarefaService.alteraTarefa(tarefaDTO, id));
    }
}
