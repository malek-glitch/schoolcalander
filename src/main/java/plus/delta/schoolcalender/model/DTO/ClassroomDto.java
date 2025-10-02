package plus.delta.schoolcalender.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import plus.delta.schoolcalender.model.Classroom;

import java.io.Serializable;

/**
 * DTO for {@link plus.delta.schoolcalender.model.Classroom}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ClassroomDto(Long id, String name, String description) implements Serializable {

    public ClassroomDto(Classroom classroom) {
        this(classroom.id(), classroom.name(), classroom.description());
    }
}