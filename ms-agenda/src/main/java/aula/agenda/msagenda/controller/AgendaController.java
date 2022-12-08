package aula.agenda.msagenda.controller;

import aula.agenda.msagenda.controller.dto.AgendaDTO;
import aula.agenda.msagenda.repository.AgendaRepository;
import aula.agenda.msagenda.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

  @Autowired
  private AgendaService agendaService;

  @Autowired
  private AgendaRepository agendaRepository;

  @GetMapping("/")
  public List<AgendaDTO> listarTodasAgenda(){
    return agendaService.listarAgendas();
  }


  @PostMapping("/")
  public void inserirAgenda(@RequestBody AgendaDTO agendaDTO) {

    agendaService.salvar(agendaDTO);
  }

}
