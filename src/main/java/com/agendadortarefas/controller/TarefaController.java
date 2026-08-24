package com.agendadortarefas.controller;

import com.agendadortarefas.business.TarefaService;
import com.agendadortarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> criaTarefa(@RequestBody TarefaDTO tarefaDTO, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.criaTarefa(token, tarefaDTO));
    }
}
