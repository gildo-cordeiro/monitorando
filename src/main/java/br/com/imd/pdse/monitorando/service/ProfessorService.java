package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Professor;
import br.com.imd.pdse.monitorando.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(final ProfessorRepository repository) {
        this.repository = repository;
    }

    public void findById(Professor professor){
        repository.findById(professor.getId());
    }

    public void cadastrar(Professor professor){
        repository.save(professor);
    }


}
