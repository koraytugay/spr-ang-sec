package biz.tugay.sprangsec.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200",
    allowCredentials = "true",
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.OPTIONS})
public class RoleController {

  @GetMapping("/role")
  public Map<String, String> getRole(Authentication authentication) {
    GrantedAuthority grantedAuthority = authentication.getAuthorities().iterator().next();
    Map<String, String> userRole = new HashMap<>();
    userRole.put("role", grantedAuthority.getAuthority());
    return userRole;
  }
}
