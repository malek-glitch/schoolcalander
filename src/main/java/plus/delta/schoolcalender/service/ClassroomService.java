package plus.delta.schoolcalender.service;

import org.springframework.stereotype.Service;
import plus.delta.schoolcalender.model.Classroom;
import plus.delta.schoolcalender.model.DTO.ClassroomDto;
import plus.delta.schoolcalender.model.DTO.SessionDto;
import plus.delta.schoolcalender.model.DTO.StudentDto;
import plus.delta.schoolcalender.model.Session;
import plus.delta.schoolcalender.repository.ClassroomRepository;

import java.util.List;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepo;

    public ClassroomService(ClassroomRepository classroomRepo) {
        this.classroomRepo = classroomRepo;
    }

    public ClassroomDto save(ClassroomDto classroom) {
        Classroom saved = classroomRepo.save(new Classroom(classroom));
        return new ClassroomDto(saved);
    }

    public ClassroomDto findById(Long id) {
        Classroom classroom = classroomRepo.findById(id).get();
        return new ClassroomDto (classroom);
    }

    public List<ClassroomDto> getAll() {
        List<Classroom> all = classroomRepo.findAll();
        return all.stream().map(ClassroomDto::new).toList();
    }

    public void deleteById(Long id) {
        classroomRepo.deleteById(id);
    }

    public List<SessionDto> getClassroomSessions(Long id) {
        Classroom classroom = classroomRepo.findById(id).get();
        return classroom.sessions().stream().map(SessionDto::new).toList();
    }

    public List<StudentDto> getClassroomStudents(Long id) {
        Classroom classroom = classroomRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Classroom not found with id: " + id));
        return classroom.students().stream().map(StudentDto::new).toList();
    }
}
