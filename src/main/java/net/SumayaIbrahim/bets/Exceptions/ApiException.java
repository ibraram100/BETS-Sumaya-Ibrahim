// This class is responsible for making exceptions whenever an error occurs
package net.SumayaIbrahim.bets.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApiException extends RuntimeException{
    private HttpStatus status;
    private String message;


}
