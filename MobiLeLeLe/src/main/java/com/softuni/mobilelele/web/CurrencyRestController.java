package com.softuni.mobilelele.web;

import com.softuni.mobilelele.model.dto.ConvertRequestDTO;
import com.softuni.mobilelele.model.dto.MoneyDTO;
import com.softuni.mobilelele.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyRestController {

    private final CurrencyService currencyService;

    public CurrencyRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Operation(summary = "Converts BGN to another currency")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully converted currency",
                            content =
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MoneyDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Invalid request",
                            content = @Content)
            }
    )
    @Parameter(name = "currency", description = "Currency to convert to", required = true)
    @Parameter(name = "amount", description = "Amount to convert", required = true)
    @GetMapping("/api/currency/convert")
    public MoneyDTO convert(@Valid ConvertRequestDTO convertRequestDTO) {
        return currencyService.convert(convertRequestDTO);
    }


}
