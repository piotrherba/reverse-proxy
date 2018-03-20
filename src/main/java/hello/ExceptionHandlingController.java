package hello;

import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
//import java.util.logging.Logger;

@ControllerAdvice(assignableTypes = HelloController.class)
public class ExceptionHandlingController {


//    private final static Logger LOGGER = Logger.getLogger(ExceptionHandlingController.class.getName());

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleError(Logger logger, HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

    }

}
