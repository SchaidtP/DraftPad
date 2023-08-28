package br.com.draftpad.service.auth;

import br.com.draftpad.domain.user.User;
import br.com.draftpad.infra.security.TokenService;
import br.com.draftpad.repository.IUserRepository;
import br.com.draftpad.service.auth.request.RequestAccountCredentials;
import br.com.draftpad.service.auth.response.ResponseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity<?> signin(RequestAccountCredentials requestAccountCredentials) {
        var userInvalid = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username/password supplied!");
        if (checkIfParamsIsNotNull(requestAccountCredentials)) return userInvalid;
        try {
            String userName = requestAccountCredentials.getUserName();
            String password = requestAccountCredentials.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

            User user = (User) repository.findByUserName(userName);
            if (user != null) {
                var token = tokenService.generateToken(user);
                ResponseToken tokenResponse = new ResponseToken(user.getUsername(), user.getRole().toString(), token);
                return ResponseEntity.ok(tokenResponse);
            } else {
                return userInvalid;
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private boolean checkIfParamsIsNotNull(RequestAccountCredentials data) {
        return data == null || data.getUserName() == null || data.getUserName().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }
}
