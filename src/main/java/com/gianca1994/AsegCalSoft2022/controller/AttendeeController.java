package com.gianca1994.AsegCalSoft2022.controller;


import com.gianca1994.AsegCalSoft2022.model.Attendee;
import com.gianca1994.AsegCalSoft2022.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v3.0.1/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    private boolean checkDNI(String dni) {
        return dni.matches("[+-]?\\d*(\\.\\d+)?") && dni.length() > 0;
    }

    @GetMapping()
    public ResponseEntity<Object> getAttendees() {
        try {
            return new ResponseEntity<>(
                    attendeeService.getAttendees(),
                    HttpStatus.OK
            );
        } catch (Exception error) {
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Object> getAttendeeByDni(@PathVariable String dni) {
        if (!checkDNI(dni))
            return new ResponseEntity<>("Invalid DNI format.", HttpStatus.BAD_REQUEST);

        Attendee attendee = attendeeService.getAttendeeByDni(dni);

        if (attendee != null) {
            return new ResponseEntity<>(attendee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Assistant not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveAttendee(@RequestBody Attendee attendee) {
        if (!checkDNI(attendee.getDni()))
            return new ResponseEntity<>("Invalid DNI format.", HttpStatus.BAD_REQUEST);

        Attendee oldAttendee = attendeeService.getAttendeeByDni(attendee.getDni());

        if (oldAttendee != null) {
            return new ResponseEntity<>("The assistant is already registered.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(
                    attendeeService.saveAttendee(attendee),
                    HttpStatus.OK
            );
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Object> deleteUser(@PathVariable String dni) {
        if (!checkDNI(dni))
            return new ResponseEntity<>("Invalid DNI format.", HttpStatus.BAD_REQUEST);

        Attendee deleteAttendee = attendeeService.getAttendeeByDni(dni);

        if (deleteAttendee != null) {
            attendeeService.deleteAttendee(dni);
            return new ResponseEntity<>("Attendee deleted correctly!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Assistant not found!", HttpStatus.NOT_FOUND);
        }
    }
}
