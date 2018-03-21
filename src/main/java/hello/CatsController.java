package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatsController {

    private final Logger logger = LoggerFactory.getLogger(CatFinder.class);

    @RequestMapping(method = RequestMethod.POST, produces = "application/json",
            value = "/findcats")
    public CatOutput findcats(@RequestBody CatInput imageWithCats) {
        CatFinder returnFinder = new CatFinder(imageWithCats.getImageWithCats());
        return new CatOutput(returnFinder.findCats());
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json",
            value = "/findcatswiththresholdmatch")
    public CatOutput findcatswiththresholdmatch(@RequestBody CatInputWithThreshold image_with_cats) {
        CatFinder returnFinder = new CatFinder(image_with_cats.getImageWithCats(), image_with_cats.getMatchThreshold());
        return new CatOutput(returnFinder.findCats());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        logger.error("Something went terribly wrong");
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }
}
