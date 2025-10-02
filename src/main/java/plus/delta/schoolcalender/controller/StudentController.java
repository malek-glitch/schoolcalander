package plus.delta.schoolcalender.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plus.delta.schoolcalender.model.DTO.StudentDto;
import plus.delta.schoolcalender.service.SessionService;
import plus.delta.schoolcalender.service.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;
    private final SessionService sessionService;

    public StudentController(StudentService studentService, SessionService sessionService) {
        this.studentService = studentService;
        this.sessionService = sessionService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentDto> get(@PathVariable long id) {
        return ResponseEntity.ok(studentService.get(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> create(@RequestBody StudentDto student) {
        return ResponseEntity.ok(studentService.save(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok(true);
    }

}
