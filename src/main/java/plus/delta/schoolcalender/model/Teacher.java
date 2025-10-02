package plus.delta.schoolcalender.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import plus.delta.schoolcalender.model.DTO.TeacherDto;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(fluent = true, chain = true)
public class Teacher extends User {

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    List<Session>  sessions;


    public Teacher(TeacherDto teacher) {
        super(teacher.id(), teacher.username(), teacher.email(), teacher.phone(), teacher.address());
        name(teacher.name());
        department(teacher.department());
        password(teacher.password());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"),  new SimpleGrantedAuthority("ROLE_TEACHER"));
    }
}
