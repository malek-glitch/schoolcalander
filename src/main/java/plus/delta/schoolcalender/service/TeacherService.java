package plus.delta.schoolcalender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import plus.delta.schoolcalender.model.DTO.SessionDto;
import plus.delta.schoolcalender.model.DTO.TeacherDto;
import plus.delta.schoolcalender.model.Teacher;
import plus.delta.schoolcalender.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {


    final private TeacherRepository teacherRepo;



    public List<Teacher> getAll() {
        return teacherRepo.findAll();
    }

    public Teacher getById(Long id) {
        Optional<Teacher> teacher = teacherRepo.findById(id);
        if (teacher.isEmpty()) {
            throw new IllegalArgumentException("Teacher not found with id: " + id);
        }
        return teacher.get();
    }

    public TeacherDto save(TeacherDto teacher) {
        Teacher entity = new Teacher(teacher);
        String encode = new BCryptPasswordEncoder().encode(teacher.password());
        entity.password(encode);

        Teacher saved = teacherRepo.save(entity);
        return new TeacherDto(saved);
    }

    public void deleteById(Long id) {
        teacherRepo.deleteById(id);
    }

    public Teacher update(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    public List<SessionDto> getTeacherSessions(Long teacherId) {
        return getById(teacherId).sessions().stream().map(SessionDto::new).toList();
    }

    public boolean existsByUsername(String username) {
        return teacherRepo.existsByUsername(username);
    }

    public Teacher getByUsername(String username) {
        return teacherRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found with username: " + username));
    }
}
