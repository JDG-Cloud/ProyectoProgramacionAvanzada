package co.edu.uniquindio.CommuSafe.modules.security.service;

import co.edu.uniquindio.CommuSafe.modules.security.dto.AuthRequest;
import co.edu.uniquindio.CommuSafe.modules.security.dto.AuthResponse;
import co.edu.uniquindio.CommuSafe.modules.security.implementation.SecurityServiceInterface;
import co.edu.uniquindio.CommuSafe.modules.user.model.User;
import co.edu.uniquindio.CommuSafe.modules.security.repository.SecurityRepository;
import co.edu.uniquindio.CommuSafe.modules.security.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SecurityService implements SecurityServiceInterface {

    @Autowired
    private SecurityRepository authRepository;
    @Autowired
    private JwtService jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManger;

    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        UserDetails user = authRepository.findByEmail(authRequest.getEmail()).orElseThrow();

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    public void logout(String token) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "not implemented yet");
    }
}
