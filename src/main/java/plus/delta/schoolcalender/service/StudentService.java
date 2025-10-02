package plus.delta.schoolcalender.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import plus.delta.schoolcalender.model.DTO.StudentDto;
import plus.delta.schoolcalender.model.Student;
import plus.delta.schoolcalender.repository.ClassroomRepository;
import plus.delta.schoolcalender.repository.StudentRepository;

@Service
public class StudentService {

    private StudentRepository studentRepo;
    private ClassroomRepository classroomRepo;

    public StudentService(StudentRepository studentRepo, ClassroomRepository classroomRepo) {
        this.studentRepo = studentRepo;
        this.classroomRepo = classroomRepo;
    }

    public StudentDto get(Long id) {
        Student student = studentRepo.findById(id).get();
        return new StudentDto(student);
    }

    public StudentDto save(StudentDto student) {
        classroomRepo.findById(student.classroom().id()).orElseThrow(() -> new EntityNotFoundException("classroom not found"));

        Student saved = studentRepo.save(new Student(student));
        return new StudentDto(saved);
    }

    public void delete(Long id) {
        studentRepo.deleteById(id);
    }


    public boolean existsByUsername(String username) {
        return studentRepo.existsByUsername(username);
    }

    public Student getByUsername(String username) {
        return studentRepo.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("student not found"));
    }
}
