package com.example.calculatorservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public final class LoanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCalculateInstallmentPlan() throws Exception {
        // Prepare test data
        InstallmentPlanRequest request = new InstallmentPlanRequest(new BigDecimal(1000), new BigDecimal(5), 10);

        // Send request to endpoint using MockMvc
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/loans")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].month").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].payment").value(102.31))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].principal").value(98.14))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].interest").value(4.17))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].balance").value(901.86))
                .andExpect(MockMvcResultMatchers.jsonPath("$[9].month").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[9].payment").value(102.31))
                .andExpect(MockMvcResultMatchers.jsonPath("$[9].principal").value(101.88))
                .andExpect(MockMvcResultMatchers.jsonPath("$[9].interest").value(0.42))
                .andExpect(MockMvcResultMatchers.jsonPath("$[9].balance").value(0.0));
    }
}

