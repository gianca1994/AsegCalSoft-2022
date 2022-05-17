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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAttendeeById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(
                    attendeeService.getAttendeeById(id),
                    HttpStatus.OK
            );
        } catch (Exception error) {
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveAttendee(@RequestBody Attendee attendee) {
        try {
            return new ResponseEntity<>(
                    attendeeService.saveAttendee(attendee),
                    HttpStatus.OK
            );
        } catch (Exception error) {
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        try {
            attendeeService.deleteAttendee(id);
            return new ResponseEntity<>("Attendee deleted correctly!", HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}
