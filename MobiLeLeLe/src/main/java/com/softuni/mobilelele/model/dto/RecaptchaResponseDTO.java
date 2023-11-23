package com.softuni.mobilelele.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class RecaptchaResponseDTO {
    private boolean success;
    private List<String> errorCodes;

    public RecaptchaResponseDTO() {
    }

    public boolean isSuccess() {
        return success;
    }

    public RecaptchaResponseDTO setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public List<String> getErrorCodes() {
        return errorCodes;
    }

    public RecaptchaResponseDTO setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
        return this;
    }
}
