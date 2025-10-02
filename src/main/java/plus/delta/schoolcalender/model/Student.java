package plus.delta.schoolcalender.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import plus.delta.schoolcalender.model.DTO.StudentDto;
import plus.delta.schoolcalender.model.utils.Gender;

import java.sql.Date;
import java.util.Collection;
import java.util.List;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(fluent = true, chain = true)
public class Student extends User {

    @Column(nullable = false)
    String fullName;

    @Column(nullable = false)
    Date birthday;

    @Column(nullable = false)
    Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    private Classroom classroom;

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public Student(StudentDto student) {
        super(student.id(), student.username(), student.email(), student.phone(), student.address());
        fullName(student.fullName());
        birthday(student.birthday());
        gender(student.gender());
        password(student.password());
        Classroom classroom = new Classroom().id(student.classroom().id());
        classroom(classroom);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
