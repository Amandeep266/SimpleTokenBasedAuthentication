

package com.employeeService.filter;

import com.employeeService.services.MyUserDetailService;
import com.employeeService.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
//@Order(<Precedence constant>)
      /*  If you debug the order in which spring security filter chain is invoked in your case it seems adding @Order annotation causes it to run before FilterChainProxy
      starts its processing logic and then it sets the SecurityContext to empty inside SecurityContextPersistenceFilter
      which overwrites what you set in your filter causing the behavior you see.
      Since you are trying to add a custom auth mechanism it would be recommended to add the filter before UsernamePasswordAuthenticationFilter
      which your SecurityFilterChain does but seems to be overridden by @Order annotation.
       I guess the Order annotation is redundant in this case anyway and should be removed..


        You can play around by setting the debug level logging of spring security to see how these filters are invoked.
*/
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        System.out.println(authorization);

        if (null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            userName = jwtUtility.getUsernameFromToken(token);

            System.out.println(userName);
        }


        if (null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = myUserDetailService.loadUserByUsername(userName);
            if (jwtUtility.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                System.out.println("VALIDATED");
            }
        }
        // filterChain.doFilter(request, response);}
//        if(request.getServletPath().equals("/authenticate")) {
//            filterChain.doFilter(request, response);
//        }

        filterChain.doFilter(request, response);
        System.out.println("inside filter");
    }

}
