package itmo.ib.service;

import itmo.ib.dto.JwtResponse;
import itmo.ib.dto.LoginRequest;
import itmo.ib.dto.RegisterRequest;
import itmo.ib.model.User;
import itmo.ib.repository.UserRepository;
import itmo.ib.util.XssUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        var user = findUserByUsername(request.username());
        return generateJwt(user);
    }

    public JwtResponse registerUser(RegisterRequest request) {
        User user = User.builder()
                .username(XssUtils.sanitize(request.username()))
                .password(passwordEncoder.encode(request.password()))
                .build();
        validateUsername(user.getUsername());
        user = userRepository.save(user);
        return generateJwt(user);
    }

    private JwtResponse generateJwt(User user) {
        String jwt = jwtService.generateToken(user);
        return new JwtResponse(user.getUsername(), jwt);
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    private void validateUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new AuthenticationServiceException("Username " + username + " is taken");
        }
    }
}
