package kr.co.upcoding.controller;

import kr.co.upcoding.exception.AppException;
import kr.co.upcoding.jwt.JwtTokenProvider;
import kr.co.upcoding.mapper.RoleMapper;
import kr.co.upcoding.mapper.UserMapper;
import kr.co.upcoding.payload.ApiResponse;
import kr.co.upcoding.payload.JwtAuthenticationResponse;
import kr.co.upcoding.payload.LoginRequest;
import kr.co.upcoding.payload.SignUpRequest;
import kr.co.upcoding.repository.UserRepository;
import kr.co.upcoding.vo.RoleName;
import kr.co.upcoding.vo.RoleVO;
import kr.co.upcoding.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserMapper userRepository;

    @Autowired
    RoleMapper roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserVO loginRequest, HttpServletResponse response) {
        System.out.println(loginRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        final Cookie cookie = new Cookie("jwt", jwt);
        cookie.setDomain("24localhost");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(10000);
        response.addCookie(cookie);
        response.setHeader("Authorization","Bearer "+jwt);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserVO signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername()) != null) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail()) != null) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        UserVO user = new UserVO(signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword(), true, true, true, true);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        RoleVO userRole;
        if(roleRepository.findByName("ROLE_USER") == null){
            new AppException("User Role not set.");
        }
        userRole = new RoleVO(RoleName.ROLE_USER);

        user.setRoles(Collections.singletonList(userRole));

        boolean result = userRepository.saveUser(user);
        UserVO resultVO = null;
        userRepository.saveRole(user.getRoles());
        if(result){
            resultVO = user;
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(resultVO.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

}
