package plus.delta.schoolcalender.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import plus.delta.schoolcalender.model.Student;
import plus.delta.schoolcalender.model.utils.Gender;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link plus.delta.schoolcalender.model.Student}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record StudentDto(Long id, String username, String password, String email, String phone, String address,
                         String fullName, Date birthday, Gender gender,
                         ClassroomDto classroom) implements Serializable {

    public StudentDto(Student student) {
        this(student.id(), student.username(), student.password(), student.email(), student.phone(), student.address(),
                student.fullName(), student.birthday(), student.gender(),
                new ClassroomDto(student.classroom()));
    }

}