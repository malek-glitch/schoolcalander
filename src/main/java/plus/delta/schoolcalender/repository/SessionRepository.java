package plus.delta.schoolcalender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plus.delta.schoolcalender.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
