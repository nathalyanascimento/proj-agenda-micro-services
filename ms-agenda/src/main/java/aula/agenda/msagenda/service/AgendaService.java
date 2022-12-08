package aula.agenda.msagenda.service;

import aula.agenda.msagenda.controller.dto.AgendaDTO;
import aula.agenda.msagenda.controller.dto.DadosFuncionarioDTO;
import aula.agenda.msagenda.controller.dto.DadosSalaDTO;
import aula.agenda.msagenda.model.Agenda;
import aula.agenda.msagenda.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
     private RestTemplate restTemplate;

    private static final String HTTP_FUNCIONARIO_FUNCIONARIO_ID ="http://funcionario/funcionario/";

    private static final String HTTP_SALA_SALA_ID ="http://sala/sala/";

    public  List<AgendaDTO> listarAgendas() {
        List<Agenda> agendaList = agendaRepository.findAll();
        List<AgendaDTO> dtoList = new ArrayList<>();
        for (Agenda agenda:agendaList){
            AgendaDTO agendaDTO = new AgendaDTO(agenda);
            DadosFuncionarioDTO dadosFuncionarioDTO = consumirFuncionario(agendaDTO);
            agendaDTO.setResponsavel(dadosFuncionarioDTO.getNome());
            DadosSalaDTO dadosSalaDTO = consumirSalas(agendaDTO);
            agendaDTO.setSala(dadosSalaDTO.getNome());
            dtoList.add(agendaDTO);
        }
        return dtoList;

    }
    public  DadosFuncionarioDTO consumirFuncionario(AgendaDTO agendaDTO){
        ResponseEntity<DadosFuncionarioDTO> exchangeret = restTemplate.exchange(HTTP_FUNCIONARIO_FUNCIONARIO_ID + agendaDTO.getIdResponsavel(), HttpMethod.GET, null, DadosFuncionarioDTO.class);
        DadosFuncionarioDTO dadosFuncionarioDTO = exchangeret.getBody();
        return dadosFuncionarioDTO;
    }
    public DadosSalaDTO consumirSalas(AgendaDTO agendaDTO){
        ResponseEntity<DadosSalaDTO> exchangesala = restTemplate.exchange(HTTP_SALA_SALA_ID + agendaDTO.getIdSala(), HttpMethod.GET, null, DadosSalaDTO.class);
        DadosSalaDTO dadosSalaDTO = exchangesala.getBody();
        return dadosSalaDTO;
    }

    public void salvar(AgendaDTO agendaDTO) {
        DadosSalaDTO salaDTO = consumirSalas(agendaDTO);
        DadosFuncionarioDTO funcionarioDTO = consumirFuncionario(agendaDTO);
        Agenda agenda = agendaDTO.converteParaAgenda();
        agendaRepository.save(agenda);


    }
}
