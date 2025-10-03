package plus.delta.schoolcalender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.delta.schoolcalender.model.DTO.SessionDto;
import plus.delta.schoolcalender.model.Session;
import plus.delta.schoolcalender.service.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionDto> createSession(@RequestBody SessionDto session) {
        SessionDto created = sessionService.create(session);
        System.out.println("created session: " + created);
        return ResponseEntity.ok(created);
    }
}
