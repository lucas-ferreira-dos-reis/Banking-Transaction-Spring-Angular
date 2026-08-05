package com.banking_transaction_api.banking_api.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.banking_transaction_api.banking_api.domain.dtos.TransferDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should schedule transfer via POST endpoint and return 201 Created")
    void shouldScheduleTransferSuccessfully() throws Exception {
        TransferDto dto = new TransferDto(
                "1234567890",
                "0987654321",
                new BigDecimal("500.00"),
                LocalDate.now().plusDays(3));

        mockMvc.perform(post("/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.sourceAccount").value("1234567890"))
                .andExpect(jsonPath("$.fee").value(12.00));
    }

    @Test
    @DisplayName("Should return 400 Bad Request when accounts are identical")
    void shouldReturn400WhenAccountsAreSame() throws Exception {
        TransferDto dto = new TransferDto(
                "1234567890",
                "1234567890", // Mesma conta
                new BigDecimal("500.00"),
                LocalDate.now());

        mockMvc.perform(post("/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Business Rule Violation"));
    }

    @Test
    @DisplayName("Should fetch all transfers via GET endpoint and return 200 OK")
    void shouldGetAllTransfers() throws Exception {
        mockMvc.perform(get("/transfers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
