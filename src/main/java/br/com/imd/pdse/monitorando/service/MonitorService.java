package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.Monitor;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.repository.MonitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MonitorService {

    private final MonitorRepository repository;

    public MonitorService(MonitorRepository repository) {
        this.repository = repository;
    }

    public List<Monitor> monitorList(){
        return repository.findAll();
    }

    public Monitor save(Monitor monitor){
        return repository.save(monitor);
    }

    public List<Classroom> getClassroomList(UUID userId){
        return repository.findByIdAndUserTypeMonitor(UserType.MONITOR, userId);
    }

    public Monitor findById(UUID id){
        return repository.findById(id).get();
    }
}
