package plus.delta.schoolcalender.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import plus.delta.schoolcalender.model.DTO.SessionDto;
import plus.delta.schoolcalender.model.utils.Day;
import plus.delta.schoolcalender.model.utils.Room;
import plus.delta.schoolcalender.model.utils.Time;

@Entity
@Data
@Accessors(fluent = true, chain = true)
@Builder
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String subject;

    @ManyToOne(fetch = FetchType.LAZY)
    Classroom classroom;

    @ManyToOne(fetch = FetchType.LAZY)
    Teacher teacher;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    Day day;

    @Enumerated(EnumType.STRING)
    @Column (name = "session_time")
    Time time;

    @Enumerated(EnumType.STRING)
    Room room;

    public Session() {}

    public Session(SessionDto session) {
        subject(session.subject());
        time(session.time());
        room(session.room());
        day(session.day());
        Classroom classroom = new Classroom().id(session.classroom().id());
        classroom(classroom);
        Teacher teacher = (Teacher) new Teacher().id(session.teacher().id());

        teacher(teacher);
    }

}
