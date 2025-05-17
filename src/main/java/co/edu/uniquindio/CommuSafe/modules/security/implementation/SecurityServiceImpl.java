package co.edu.uniquindio.CommuSafe.modules.security.implementation;
import co.edu.uniquindio.CommuSafe.modules.security.dto.AuthRequest;
import co.edu.uniquindio.CommuSafe.modules.security.dto.AuthResponse;
import co.edu.uniquindio.CommuSafe.modules.security.repository.SecurityRepository;
import co.edu.uniquindio.CommuSafe.modules.security.security.JwtService;
import co.edu.uniquindio.CommuSafe.modules.security.service.SecurityService;
import co.edu.uniquindio.CommuSafe.modules.user.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private SecurityRepository authRepository;
    @Autowired
    private JwtService jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManger;

    public AuthResponse authenticate(AuthRequest authRequest) {
        // Autenticación de credenciales
        authenticationManger.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        // Obtener usuario desde base de datos
        co.edu.uniquindio.CommuSafe.modules.user.model.User userDetails = authRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Verificar si la cuenta está activa (usando enumeración)
        if (userDetails.getStatus() != Status.Active) {
            throw new DisabledException("La cuenta del usuario no está activa");
        }

        // Generar y retornar el token JWT
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }



    public void logout(String token) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "not implemented yet");
    }
}
