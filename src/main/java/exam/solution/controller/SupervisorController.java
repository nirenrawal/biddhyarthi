package exam.solution.controller;

import exam.solution.model.Supervisor;
import exam.solution.repository.SupervisorRepo;
import exam.solution.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SupervisorController {

    @Autowired
    SupervisorService supervisorService;

    @Autowired
    SupervisorRepo supervisorRepo;


    @GetMapping("/supervisors")
    public ResponseEntity<List<Supervisor>> getAllSupervisors(){
        return supervisorService.getAll();
    }

    @PostMapping("/supervisors")
    public ResponseEntity<Supervisor> createSupervisor(@RequestBody Supervisor supervisor){
        return supervisorService.createNewSupervisor(supervisor);
    }

    @PutMapping("/supervisors/{supervisorId}")
    public ResponseEntity<Supervisor> updateSupervisor(@PathVariable("supervisorId") int supervisorId, @RequestBody Supervisor supervisor){
        return supervisorService.updateSupervisor(supervisorId, supervisor);
    }

    @DeleteMapping("/supervisors/{supervisorId}")
    public ResponseEntity<HttpStatus> deleteSupervisor(@PathVariable("supervisorId") int supervisorId){
        return supervisorService.deleteSupervisor(supervisorId);
    }

}
