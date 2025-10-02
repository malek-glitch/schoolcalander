package plus.delta.schoolcalender.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    final private StudentService studentService;
    final private TeacherService teacherService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails;
        if (studentService.existsByUsername(username)) {
            userDetails = studentService.getByUsername(username);
        }
        else if (teacherService.existsByUsername(username)) {
            userDetails = teacherService.getByUsername(username);
        }
        else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return userDetails;
    }
}
