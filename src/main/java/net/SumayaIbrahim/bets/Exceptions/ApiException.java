// This class is responsible for making exceptions whenever an error occurs
package net.SumayaIbrahim.bets.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ApiException extends RuntimeException{
    private HttpStatus status;
    private String message;


}
