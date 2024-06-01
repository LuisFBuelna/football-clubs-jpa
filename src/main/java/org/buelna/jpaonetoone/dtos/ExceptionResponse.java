package org.buelna.jpaonetoone.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private String message;

    private HttpStatus httpStatus;
}
