package ru.netology.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.netology.dto.AuthenticationResponse;
import ru.netology.dto.LoginRequest;
import ru.netology.security.JwtService;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;
    @Mock
    private CustomUserDetailsService userDetailsService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;

    @Mock
    private InMemoryBlackListToken tokenBlacklist;

    @Test
    public void testLogin() {

        LoginRequest request = new LoginRequest("admin", "admin");
        UserDetails userDetails = new User("admin", "admin", Collections.emptyList());
        String token = "jwtToken";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userDetailsService.loadUserByUsername("admin")).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn(token);

        AuthenticationResponse response = authenticationService.login(request);

        assertEquals(token, response.getToken());

    }

    @Test
    public void testLogout() {

        String token = "jwtToken";

        authenticationService.logout(token);

        verify(tokenBlacklist).addToBlacklist(token);

    }

}
