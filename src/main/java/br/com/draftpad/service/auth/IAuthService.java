package br.com.draftpad.service.auth;

import br.com.draftpad.service.auth.request.RequestAccountCredentials;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<?> signin(RequestAccountCredentials requestAccountCredentials);
}
