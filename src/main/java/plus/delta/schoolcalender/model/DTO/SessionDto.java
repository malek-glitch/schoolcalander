package plus.delta.schoolcalender.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import plus.delta.schoolcalender.model.Session;
import plus.delta.schoolcalender.model.utils.Day;
import plus.delta.schoolcalender.model.utils.Room;
import plus.delta.schoolcalender.model.utils.Time;

import java.io.Serializable;

/**
 * DTO for {@link plus.delta.schoolcalender.model.Session}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SessionDto(Long id, String subject, ClassroomDto classroom, TeacherDto teacher, Day day, Time time,
                         Room room) implements Serializable {
    public SessionDto(Session s) {
        this(s.id(), s.subject(), new ClassroomDto(s.classroom()), new TeacherDto(s.teacher()), s.day(), s.time(), s.room());


    }
}