package plus.delta.schoolcalender.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plus.delta.schoolcalender.model.DTO.ClassroomDto;
import plus.delta.schoolcalender.model.DTO.SessionDto;
import plus.delta.schoolcalender.model.DTO.StudentDto;
import plus.delta.schoolcalender.service.ClassroomService;

import java.util.List;

@RestController
@RequestMapping("classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public ResponseEntity<List<ClassroomDto>> findAll() {
        return ResponseEntity.ok(classroomService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.findById(id));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDto>> getClassroomStudents(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.getClassroomStudents(id));
    }

    @GetMapping("/{id}/sessions")
    public ResponseEntity<List<SessionDto>> getClassroomSessions(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.getClassroomSessions(id));
    }

    @PostMapping
    public ResponseEntity<ClassroomDto> save(@RequestBody ClassroomDto classroom) {
        return ResponseEntity.ok(classroomService.save(classroom));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        classroomService.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @PutMapping
    public ResponseEntity<ClassroomDto> update(@RequestBody ClassroomDto classroom) {
        return ResponseEntity.ok(classroomService.save(classroom));
    }
}
