package plus.delta.schoolcalender.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.proxy.HibernateProxy;
import plus.delta.schoolcalender.model.DTO.ClassroomDto;

import java.util.List;


@Entity
@Data
@Accessors(fluent = true, chain = true)
@Builder
@AllArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    List<Student> students;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    List<Session> sessions;

    public Classroom() {}

    public Classroom(ClassroomDto classroom) {

        name(classroom.name());
        description(classroom.description());
    }
}
