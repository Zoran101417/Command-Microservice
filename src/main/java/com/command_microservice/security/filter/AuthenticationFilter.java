//package com.command_microservice.security.filter;
//
//
//import com.spring_cloud_gateway.security.TokenManager;
//import com.spring_cloud_gateway.service.UtilService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//@Slf4j
//@Component
//public class AuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private TokenManager tokenManager;
//
////    @Autowired
////    private PermissionsRepository permissionsRepository;
//
////    @Autowired
////    private UserService userService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            if (isLoginRequest(request)) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//            String authHeader = request.getHeader("Authorization");
//
//            if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                String token = authHeader.substring(7);
//                String userEmail = tokenManager.getAuthentication(token);
//
//                if (userEmail != null) {
////                List<String> permissions = permissionsRepository.getUserPermissionsByEmail(userEmail);
//                    List<String> permissions = Arrays.asList("GET", "UPDATE", "CREATE", "ADMIN", "DELETE", "DELETE ALL");
//
//                    if (!permissions.isEmpty()) {
//                        List<GrantedAuthority> authList = tokenManager.getAuthorities(permissions);
//
//                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                                userEmail, request.getHeader("Authorization").replace("Bearer ", ""), authList);
//
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
//                        filterChain.doFilter(request, response);
//                    } else {
//                        handleNoPermissions(response);
//                    }
//                } else {
//                    handleNoPermissions(response);
//                }
//            } else {
//                handleNoPermissions(response);
//            }
//        } catch (Exception e) {
//            log.error("An error occurred: " + e.getMessage());
//            handleUnauthorizedError(response, e.getMessage());
//        }
//    }
//
//    private boolean isLoginRequest(HttpServletRequest request) {
//        String requestURI = request.getRequestURI();
//        return requestURI != null && requestURI.endsWith("/login");
//    }
//
//    private void handleNoPermissions(HttpServletResponse response) throws IOException {
//        log.error("User has no permissions granted");
//        handleUnauthorizedError(response, "No Authorization provided");
//    }
//
//    private void handleUnauthorizedError(HttpServletResponse response, String errorMessage) throws IOException {
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        ResponseEntity<Object> responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
//        response.getWriter().write(UtilService.convertObjectToJson(responseEntity));
//    }
//}
