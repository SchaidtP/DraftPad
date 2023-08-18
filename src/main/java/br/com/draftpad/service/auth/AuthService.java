package br.com.draftpad.service.auth;

import br.com.draftpad.model.entity.User;
import br.com.draftpad.repository.IUserRepository;
import br.com.draftpad.security.jwt.JwtTokenProvider;
import br.com.draftpad.service.auth.request.RequestAccountCredentials;
import br.com.draftpad.service.auth.response.ResponseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public ResponseEntity<?> signin(RequestAccountCredentials requestAccountCredentials) {
        if (checkIfParamsIsNotNull(requestAccountCredentials)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        try {
            String userName = requestAccountCredentials.getUserName();
            String password = requestAccountCredentials.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

            User user = iUserRepository.findByUserName(userName);
            if (user != null) {
                ResponseToken tokenResponse = tokenProvider.createAccessToken(userName, user.getRoles());
                return ResponseEntity.ok(tokenResponse);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
            }
        }
        catch (AuthenticationException e) {
            String errorMessage = "Invalid username/password supplied!";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }
    }

    private boolean checkIfParamsIsNotNull(RequestAccountCredentials data) {
        return data == null || data.getUserName() == null || data.getUserName().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }
}
