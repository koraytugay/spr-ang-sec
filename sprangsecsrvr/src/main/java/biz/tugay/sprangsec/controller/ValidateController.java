package biz.tugay.sprangsec.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("validate")
@CrossOrigin(origins = "http://localhost:4200",
    allowCredentials = "true",
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.OPTIONS})
public class ValidateController {

  // Endpoint to be used by front end client.
  // This endpoint does not need to do anything, it will already return Success if Spring Security
  // passes, otherwise a 401 will be returned.
  @GetMapping
  public void isUserValid() {
  }
}
