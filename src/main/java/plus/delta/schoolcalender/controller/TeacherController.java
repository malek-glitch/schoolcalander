package plus.delta.schoolcalender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import plus.delta.schoolcalender.model.DTO.SessionDto;
import plus.delta.schoolcalender.model.DTO.TeacherDto;
import plus.delta.schoolcalender.model.Session;
import plus.delta.schoolcalender.model.Teacher;
import plus.delta.schoolcalender.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    final private TeacherService teacherService;

    @GetMapping
    ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<Teacher> all = teacherService.getAll();
        return ResponseEntity.ok().body(all.stream().map(TeacherDto::new).toList());
    }

    @GetMapping("/{id}")
    ResponseEntity<TeacherDto> getTeacherById(@PathVariable long id) {
        Teacher teacher = teacherService.getById(id);
        return ResponseEntity.ok(new TeacherDto(teacher));
    }

    @PostMapping
    ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacher) {
        return ResponseEntity.ok(teacherService.save(teacher));
    }

    @GetMapping("/{id}/sessions")
    ResponseEntity<List<SessionDto>> getAllTeacherSessions(@PathVariable String id) {
        Teacher teacher = teacherService.getById(Long.parseLong(id));
        return ResponseEntity.ok(teacher.sessions().stream().map(SessionDto::new).toList());
    }
}
