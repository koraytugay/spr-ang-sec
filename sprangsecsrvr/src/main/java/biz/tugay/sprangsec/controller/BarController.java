package biz.tugay.sprangsec.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bar")
public class BarController {

  @GetMapping(produces = "text/html")
  @CrossOrigin(origins = "http://localhost:4200",
      allowCredentials = "true",
      allowedHeaders = "*",
      methods = {RequestMethod.GET, RequestMethod.OPTIONS}) public String bar() {
    return "bar";
  }
}
