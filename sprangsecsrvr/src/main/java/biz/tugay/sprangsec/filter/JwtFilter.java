package biz.tugay.sprangsec.filter;

import biz.tugay.sprangsec.service.JwtService;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

// Looks for Authorization Bearer header
public class JwtFilter extends BasicAuthenticationFilter {

  private JwtService jwtService;

  public JwtFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    Cookie[] cookies = request.getCookies();
    Cookie jwtToken = null;

    if (cookies == null) {
      chain.doFilter(request, response);
      return;
    }

    for (Cookie cookie : cookies) {
      if ("jwtToken".equals(cookie.getName())) {
        jwtToken = cookie;
      }
    }

    if (jwtToken == null) {
      chain.doFilter(request, response);
      return;
    }

    if (jwtService == null) {
      ServletContext servletContext = request.getServletContext();
      WebApplicationContext webApplicationContext =
          WebApplicationContextUtils.getWebApplicationContext(servletContext);
      jwtService = webApplicationContext.getBean(JwtService.class);
    }

    Authentication usernamePasswordAuthenticationToken = getAuthentication(jwtToken.getValue());

    if (usernamePasswordAuthenticationToken == null) {
      jwtToken.setMaxAge(0);
      jwtToken.setHttpOnly(true);
      response.addCookie(jwtToken);
    }
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(String jwtToken) {
    try {
      String payload = jwtService.validateToken(jwtToken);

      JsonParser jsonParser = JsonParserFactory.getJsonParser();
      Map<String, Object> stringObjectMap = jsonParser.parseMap(payload);

      String username = stringObjectMap.get("username").toString();
      String role = stringObjectMap.get("role").toString();

      SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role);

      return new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(grantedAuthority));
    } catch (Exception e) {
      // token not valid
      return null;
    }
  }
}
