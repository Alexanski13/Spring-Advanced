package bg.softuni.caching.web;

import bg.softuni.caching.model.StudentDTO;
import bg.softuni.caching.service.StudentServiceIfc;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    private final StudentServiceIfc studentServiceIfc;

    public StudentController(StudentServiceIfc studentServiceIfc) {
        this.studentServiceIfc = studentServiceIfc;
    }

    @GetMapping("/students/all")
    public ResponseEntity<List<StudentDTO>> findAll() {

        List<StudentDTO> studentDTOS = studentServiceIfc.getAllStudents();

        studentDTOS.forEach(System.out::println);

        return ResponseEntity.ok(studentDTOS);
    }

    @GetMapping("/students/all/evict")
    public ResponseEntity<List<StudentDTO>> findAllAndEvict(){

        List<StudentDTO> studentDTOS = studentServiceIfc.getAllStudents();

        studentServiceIfc.refreshStudents();

        return ResponseEntity.ok(studentDTOS);
    }

    @GetMapping("/students/find")
    public ResponseEntity<StudentDTO> findStudentByName(@RequestParam("q") String q) {
        Optional<StudentDTO> studentOpt = studentServiceIfc.getStudentByName(q);

        return studentOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
