package exam.solution.controller;

import exam.solution.model.Student;
import exam.solution.repository.StudentRepo;
import exam.solution.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepo studentRepo;


    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        return studentService.getAll();
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return studentService.createNewStudent(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student student){
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") int id){
        return studentService.deleteStudent(id);
    }

}
