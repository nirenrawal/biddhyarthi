package exam.solution.service;

import exam.solution.model.Student;
import exam.solution.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public ResponseEntity<List<Student>> getAll() {
        try {
            List<Student> students = studentRepo.findAll();
            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(students, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Student> createNewStudent(@RequestBody Student student) {
        try {
            Student _student = studentRepo.save(student);
            return new ResponseEntity<>(_student, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        Optional<Student> studentData = studentRepo.findById(studentId);
        if (studentData.isPresent()) {
            Student _student = studentData.get();
            _student.setFullName(student.getFullName());
            _student.setEmail(student.getEmail());
            _student.setSupervisorId(student.getSupervisorId());
            return new ResponseEntity<>(studentRepo.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("studentId") int studentId) {
        try {
            studentRepo.deleteById(studentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
