package biz.tugay.sprangsec.controller;

import biz.tugay.sprangsec.service.JwtService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

  private final JwtService jwtService;

  public ValidateController(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  // Endpoint to be used by front end client.
  // This endpoint returns the generated jwtToken in json {jwtToken: ''} if the basic authentication
  // is successful.
  @GetMapping
  public Map<String, String> isUserValid() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    User principal = (User) authentication.getPrincipal();

    String principalUsername = principal.getUsername();
    // We work with single role per user, chop off ROLE_ part by substring
    String principalRole = principal.getAuthorities().iterator().next().toString().substring(5);

    Map<String, String> jwtTokenJson = new HashMap<>();
    jwtTokenJson.put("jwtToken", jwtService.generateToken(principalUsername, principalRole));

    return jwtTokenJson;
  }
}
