package com.burundibuhire.burundi.buhire.handlers;

import java.util.ArrayList;
import java.util.List;
import com.burundibuhire.burundi.buhire.exceptions.ErrorCodes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private Integer httpCode;

    private ErrorCodes code;

    private String message;

    private List<String> errors = new ArrayList<>();
}
