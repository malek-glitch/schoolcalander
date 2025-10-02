package plus.delta.schoolcalender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plus.delta.schoolcalender.model.Teacher;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    boolean existsByUsername(String username);
    Optional<Teacher> findByUsername(String username);
}
