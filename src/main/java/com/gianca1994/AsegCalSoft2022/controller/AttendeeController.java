package com.gianca1994.AsegCalSoft2022.controller;


import com.gianca1994.AsegCalSoft2022.dto.AttendeeDTO;
import com.gianca1994.AsegCalSoft2022.model.Attendee;
import com.gianca1994.AsegCalSoft2022.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("api/v3.0.1/attendees")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @GetMapping()
    public ArrayList<Attendee> getAttendees() {
       return attendeeService.getAttendees();
    }

    @GetMapping("/{dni}")
    public Attendee getAttendeeByDni(@PathVariable String dni) {
        return attendeeService.getAttendeeByDni(dni);
    }

    @PostMapping()
    public Attendee saveAttendee(@RequestBody AttendeeDTO attendee) {
        return attendeeService.saveAttendee(attendee);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Object> deleteAttendee(@PathVariable String dni) {
        return attendeeService.deleteAttendee(dni);
    }
}
