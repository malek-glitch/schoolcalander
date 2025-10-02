package plus.delta.schoolcalender.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import plus.delta.schoolcalender.model.Teacher;

import java.io.Serializable;

/**
 * DTO for {@link plus.delta.schoolcalender.model.Teacher}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TeacherDto(
        Long id, String username, String password,
        String email, String phone, String address,
        String name, String department
) implements Serializable {

    public TeacherDto(Teacher teacher) {
        this(teacher.id(), teacher.username(), teacher.password(), teacher.email(), teacher.phone(), teacher.address(),
                teacher.name(), teacher.department());
    }
}