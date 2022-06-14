package com.gianca1994.AsegCalSoft2022;

import com.gianca1994.AsegCalSoft2022.dto.AttendeeDTO;
import com.gianca1994.AsegCalSoft2022.exception.EntityNotFoundException;
import com.gianca1994.AsegCalSoft2022.exception.EntityNotValidException;
import com.gianca1994.AsegCalSoft2022.model.Attendee;
import com.gianca1994.AsegCalSoft2022.repository.AttendeeRepository;
import com.gianca1994.AsegCalSoft2022.service.AttendeeService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Objects;

@SpringBootTest
class TestAttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private AttendeeService attendeeService;

    private final Attendee ATTENDEE_JUAN = new Attendee(1, "12345678", "Juan", "Perez", "666666666", "01/01/2000", "https://www.google.com", false);
    private final Attendee ATTENDEE_FLORENCIA = new Attendee(2, "12345679", "Florencia", "Gonzalez", "666666667", "01/01/2020", "https://www.google.com", true);

    @Test
    private void preTest(int numRegister) {
        attendeeRepository.deleteAll();

        switch (numRegister) {
            case 1:
                attendeeRepository.save(ATTENDEE_JUAN);
                break;
            case 2:
                attendeeRepository.save(ATTENDEE_FLORENCIA);
                break;
            case 3:
                attendeeRepository.save(ATTENDEE_JUAN);
                attendeeRepository.save(ATTENDEE_FLORENCIA);
                break;
            default:
                break;
        }
    }

    @Test
    public void givenAttendeeQueryList_whenGetAllAttendees_thenReturnAllObject() {
        preTest(3);

        //when
        ArrayList<Attendee> listAttendee = attendeeService.getAttendees();

        //then
        assertThat(attendeeRepository.findAll().size() == listAttendee.size());
    }

    @Test
    public void givenAttendeeQueryList_whenGetAttendeeByDni_thenReturnObject() {
        preTest(1);

        //when
        Attendee attendee = attendeeService.getAttendeeByDni(ATTENDEE_JUAN.getDni());

        //then
        assertThat(Objects.equals(attendee.getDni(), "12345678"));
    }

    @Test
    public void givenAttendeeQueryList_whenGetAttendeeByDni_thenReturnNotFound() {
        preTest(1);

        //when
        //then
        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            attendeeService.getAttendeeByDni("12345672");
        });
    }

    @Test
    public void givenAttendeeQueryList_whenGetAttendeeByDni_thenReturnBadRequest() {
        preTest(1);

        //when
        //then
        org.junit.jupiter.api.Assertions.assertThrows(EntityNotValidException.class, () -> {
            attendeeService.getAttendeeByDni("asd");
        });

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotValidException.class, () -> {
            attendeeService.getAttendeeByDni("asd12345678");
        });

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotValidException.class, () -> {
            attendeeService.getAttendeeByDni("12345678asd");
        });
        org.junit.jupiter.api.Assertions.assertThrows(EntityNotValidException.class, () -> {
            attendeeService.getAttendeeByDni("a12345s678d");
        });
    }

    @Test
    public void givenAttendeeModelToSave_whenSaveAttendee_thenReturnModelAttendee() {
        preTest(0);

        //when
        Attendee newAttendee = new Attendee(3, "38416802", "testName", "testSurname", "666666666", "01/01/2000", "https://www.google.com", false);
        attendeeRepository.save(newAttendee);

        Attendee attendee = attendeeService.getAttendeeByDni("38416802");

        //then
        assertThat(Objects.equals(attendee, newAttendee));
    }

    @Test
    public void givenAttendeeModelToSave_whenDontSaveAttendee_thenReturnBadRequest() {
        preTest(0);

        //when
        AttendeeDTO newAttendee = new AttendeeDTO("", "testName", "testSurname", "666666666", "01/01/2000", "https://www.google.com");

        //then
        org.junit.jupiter.api.Assertions.assertThrows(EntityNotValidException.class, () -> {
            attendeeService.saveAttendee(newAttendee);
        });
    }

    @Test
    public void givenAttendeeDuplicatedDNI_whenDontSaveAttendee_thenReturnBadRequest() {
        preTest(1);

        //when
        AttendeeDTO newAttendee = new AttendeeDTO("12345678", "testName", "testSurname", "666666666", "01/01/2000", "https://www.google.com");

        //then
        org.junit.jupiter.api.Assertions.assertThrows(EntityNotValidException.class, () -> {
            attendeeService.saveAttendee(newAttendee);
        });
    }

    @Test
    public void givenAttendeeDniToDelete_whenDeleteAttendee_thenReturnDataBaseEmpty() {
        preTest(1);

        //when
        attendeeService.deleteAttendee("12345678");
        //then
        assertThat(attendeeRepository.findAll().size() == 0);
    }

    @Test
    public void givenAttendeeDniToDelete_whenDeleteAttendee_thenReturnStatusCodeOK() {
        preTest(1);

        //when
        //then
        assertThat(attendeeService.deleteAttendee("12345678").getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void givenAttendeeDniToDelete_whenDeleteAttendee_thenReturnNotFound() {
        preTest(1);

        //when
        //then
        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            attendeeService.deleteAttendee("12345672");
        });
    }

    @Test
    public void givenAttendeeDniToDelete_whenDeleteAttendee_thenReturnBadRequest() {
        preTest(1);

        //when
        //then
        org.junit.jupiter.api.Assertions.assertThrows(EntityNotValidException.class, () -> {
            attendeeService.deleteAttendee("asd");
        });

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotValidException.class, () -> {
            attendeeService.deleteAttendee("asd12345678");
        });

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotValidException.class, () -> {
            attendeeService.deleteAttendee("12345678asd");
        });
        org.junit.jupiter.api.Assertions.assertThrows(EntityNotValidException.class, () -> {
            attendeeService.deleteAttendee("a12345s678d");
        });
    }

}
