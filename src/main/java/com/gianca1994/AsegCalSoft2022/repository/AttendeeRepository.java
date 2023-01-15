package com.gianca1994.AsegCalSoft2022.repository;

import com.gianca1994.AsegCalSoft2022.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Integer> {
    Attendee findAttendeeByDni(String dni);
}
