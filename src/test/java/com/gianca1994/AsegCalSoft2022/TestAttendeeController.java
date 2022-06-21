package com.gianca1994.AsegCalSoft2022;


import com.gianca1994.AsegCalSoft2022.controller.AttendeeController;
import com.gianca1994.AsegCalSoft2022.dto.AttendeeDTO;
import com.gianca1994.AsegCalSoft2022.model.Attendee;
import com.gianca1994.AsegCalSoft2022.repository.AttendeeRepository;
import com.gianca1994.AsegCalSoft2022.service.AttendeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;

@WebAppConfiguration
@EnableWebMvc
@SpringBootTest(classes = AttendeeController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAttendeeController {

    private final Attendee ATTENDEE_JUAN = new Attendee(1,"12345678", "Juan", "Perez", "666666666", "01/01/2000", "https://www.google.com", false);
    private final Attendee ATTENDEE_FLORENCIA = new Attendee(2,"12345679", "Florencia", "Gonzalez", "666666667", "01/01/2020", "https://www.google.com", true);

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private AttendeeService attendeeService;

    @Autowired
    WebApplicationContext context;

    @InjectMocks
    AttendeeController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
        mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void preTest(int numRegister) {
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
    public void givenAttendeeQueryList_whenGetAllAttendees_thenReturnAllObject() throws Exception {
        preTest(3);

        mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup(context).build();

        //given
        when(attendeeService.getAttendees()).thenReturn((ArrayList<Attendee>) attendeeRepository.findAll());

        //when
        RequestBuilder request = MockMvcRequestBuilders.get("/api/v3.0.1/attendees").accept("application/json");

        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    public void givenAttendeeQueryList_whenGetAllAttendees_thenReturnAllObject_withEmptyList() throws Exception {
        preTest(0);

        mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup(context).build();

        //given
        when(attendeeService.getAttendees()).thenReturn((ArrayList<Attendee>) attendeeRepository.findAll());

        //when
        RequestBuilder request = MockMvcRequestBuilders.get("/api/v3.0.1/attendees").accept("application/json");

        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    public void givenAttendeeQueryList_whenGetAllAttendees_thenReturnAllObject_withNullList() throws Exception {
        preTest(1);

        mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup(context).build();

        //given
        when(attendeeService.getAttendees()).thenReturn((ArrayList<Attendee>) attendeeRepository.findAll());

        //when
        RequestBuilder request = MockMvcRequestBuilders.get("/api/v3.0.1/attendees").accept("application/json");

        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    public void givenAttendeeQueryList_whenGetAllAttendees_thenReturnAllObject_withNullList_withEmptyList() throws Exception {
        preTest(0);

        mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup(context).build();

        //given
        when(attendeeService.getAttendees()).thenReturn((ArrayList<Attendee>) attendeeRepository.findAll());

        //when
        RequestBuilder request = MockMvcRequestBuilders.get("/api/v3.0.1/attendees").accept("application/json");

        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

}
