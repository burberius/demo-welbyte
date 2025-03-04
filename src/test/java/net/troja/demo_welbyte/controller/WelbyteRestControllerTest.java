package net.troja.demo_welbyte.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WelbyteRestControllerTest {
    public static final String PATH = "/eligibility";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void authorized_employee_code_missing() throws Exception {
        mockMvc.perform(authenticatedRequest()).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("employee_code")));
    }

    @Test
    void authorized_member_status_missing() throws Exception {
        mockMvc.perform(authenticatedRequest()
                        .param("employee_code", "abc"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("member_status")));
    }

    @Test
    void authorized_employee_date_of_birth_missing() throws Exception {
        mockMvc.perform(authenticatedRequest()
                        .param("employee_code", "abc")
                        .param("member_status", "employee"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("employee_date_of_birth")));
    }

    @Test
    void authorized_employee_but_employee_id_missing() throws Exception {
        mockMvc.perform(authenticatedRequest()
                        .param("employee_code", "abc")
                        .param("member_status", "employee")
                        .param("employee_date_of_birth", "2000-01-01"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("employee_id")));
    }

    @Test
    void authorized_dependent_but_firstname_missing() throws Exception {
        mockMvc.perform(authenticatedRequest()
                        .param("employee_code", "abc")
                        .param("member_status", "dependent")
                        .param("employee_date_of_birth", "2000-01-01"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("employee_first_name")));
    }

    @Test
    void authorized_dependent_but_lastname_missing() throws Exception {
        mockMvc.perform(authenticatedRequest()
                        .param("employee_code", "abc")
                        .param("member_status", "dependent")
                        .param("employee_date_of_birth", "2000-01-01")
                        .param("employee_first_name", "Alf"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("employee_last_name")));
    }

    @Test
    void allFine() throws Exception {
        mockMvc.perform(authenticatedRequest()
                        .param("employee_code", "abc")
                        .param("member_status", "dependent")
                        .param("employee_date_of_birth", "1979-01-09")
                        .param("employee_first_name", "Walter")
                        .param("employee_last_name", "Jacobson"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith("{\"memberUniqueId\":44000100,\"firstName\":\"Walter\",\"lastName\":\"Jacobson\",\"dateOfBirth\":\"1979-01-09\",\"gender\":\"M\"")));
    }

    @Test
    void notFound() throws Exception {
        mockMvc.perform(authenticatedRequest()
                        .param("employee_code", "abc")
                        .param("member_status", "employee")
                        .param("employee_date_of_birth", "1942-02-09")
                        .param("employee_id", "44000112"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void unauthorized() throws Exception {
        mockMvc.perform(get(PATH)).andDo(print()).andExpect(status().isUnauthorized());
    }

    public MockHttpServletRequestBuilder authenticatedRequest() {
        return MockMvcRequestBuilders.get(PATH)
                .header("Authorization", "Bearer 1234567890");
    }
}