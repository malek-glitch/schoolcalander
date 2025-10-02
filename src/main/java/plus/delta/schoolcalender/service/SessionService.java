package plus.delta.schoolcalender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.delta.schoolcalender.model.DTO.SessionDto;
import plus.delta.schoolcalender.model.Session;
import plus.delta.schoolcalender.model.utils.Day;
import plus.delta.schoolcalender.model.utils.Room;
import plus.delta.schoolcalender.model.utils.Time;
import plus.delta.schoolcalender.repository.SessionRepository;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepo;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassroomService classroomService;

    public SessionService(SessionRepository sessionRepos, TeacherService teacherService, ClassroomService classroomService) {
        this.sessionRepo = sessionRepos;
        this.teacherService = teacherService;
        this.classroomService = classroomService;
    }

    public SessionDto create(SessionDto session) {
        Day day = session.day();
        Time time = session.time();
        Room room = session.room();

        // check if the classroom is available at the time

        List<SessionDto> sessions = classroomService.getClassroomSessions(session.classroom().id());
        for (SessionDto s : sessions) {
            if (s.day().equals(day) && s.time().equals(time)) {
                throw new RuntimeException("Date and Time are reserved (classroom)");
            }
        }
        // check if the teacher is available at the time
        sessions = teacherService.getTeacherSessions(session.teacher().id());
        for (SessionDto s : sessions) {
            if (s.day().equals(day) && s.time().equals(time)) {
                throw new RuntimeException("Date and Time are reserved (teacher)");
            }
        }

        // check if the room is available at the time
        sessions = getAll();
        sessions.forEach(s -> {
            if (s.room().equals(room) && s.time().equals(time) && s.day().equals(day)) {
                throw new RuntimeException("Room is reserved");
            }
        });

        Session saved = sessionRepo.save(new Session(session));
        return new SessionDto(saved);
    }

    public Session getById(Long id) {
        return sessionRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session not found with id: " + id));
    }

    public Session update(Session session) {
        return sessionRepo.save(session);
    }

    public List<SessionDto> getAll() {
        return sessionRepo.findAll().stream().map(SessionDto::new).toList();
    }

    public void delete(Long id) {
        sessionRepo.deleteById(id);
    }
}
