package exam.solution.service;

import exam.solution.model.Supervisor;
import exam.solution.repository.SupervisorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisorService {
    
    @Autowired
    SupervisorRepo supervisorRepo;

    public ResponseEntity<List<Supervisor>> getAll() {
        try {
            List<Supervisor> supervisors = supervisorRepo.findAll();
            if (supervisors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(supervisors, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Supervisor> createNewSupervisor(@RequestBody Supervisor supervisor) {
        try {
            Supervisor _supervisor = supervisorRepo.save(supervisor);
            return new ResponseEntity<>(_supervisor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Supervisor> updateSupervisor(@PathVariable("supervisorId") int supervisorId, @RequestBody Supervisor supervisor) {
        Optional<Supervisor> supervisorData = supervisorRepo.findById(supervisorId);
        if (supervisorData.isPresent()) {
            Supervisor _supervisor = supervisorData.get();
            supervisor.setName(supervisor.getName());
            return new ResponseEntity<>(supervisorRepo.save(_supervisor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteSupervisor(@PathVariable("supervisorId") int supervisorId) {
        try {
            supervisorRepo.deleteById(supervisorId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
