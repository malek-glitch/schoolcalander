package plus.delta.schoolcalender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import plus.delta.schoolcalender.model.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
