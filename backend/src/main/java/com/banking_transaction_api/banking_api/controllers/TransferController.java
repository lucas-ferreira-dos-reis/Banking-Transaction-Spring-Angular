package com.banking_transaction_api.banking_api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_transaction_api.banking_api.domain.dtos.TransferDto;
import com.banking_transaction_api.banking_api.domain.models.Transfer;
import com.banking_transaction_api.banking_api.services.TransferService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Transfers", description = "Endpoints for scheduling and retrieving financial transfers")
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    @Operation(summary = "Schedule a new financial transfer", description = "Calculates the applicable fee based on the transfer date and schedules the transaction.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transfer scheduled successfully", content = @Content(schema = @Schema(implementation = Transfer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request payload or fee not applicable for the selected date range", content = @Content)
    })
    public ResponseEntity<Transfer> scheduleTransfer(@Valid @RequestBody TransferDto dto) {
        Transfer createdTransfer = transferService.scheduleTransfer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransfer);
    }

    @GetMapping
    @Operation(summary = "Get all scheduled transfers", description = "Retrieves a complete list of all recorded financial transfer schedules.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of transfers", content = @Content)
    })
    public ResponseEntity<List<Transfer>> getAllTransfers() {
        List<Transfer> transfers = transferService.findAll();
        return ResponseEntity.ok(transfers);
    }

}
