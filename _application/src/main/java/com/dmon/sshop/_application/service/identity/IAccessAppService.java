package com.dmon.sshop._application.service.identity;

import com.dmon.sshop._domain.identity.model.request.Oauth2LoginRequest;
import com.dmon.sshop._domain.identity.model.request.UsernameLoginRequest;
import com.dmon.sshop._domain.identity.model.response.AuthenticationResponse;
import org.springframework.http.HttpHeaders;

public interface IAccessAppService {

    AuthenticationResponse loginUsernameForAdmin(UsernameLoginRequest request, HttpHeaders headers);

    AuthenticationResponse loginUsernameForSeller(UsernameLoginRequest request, HttpHeaders headers);

    AuthenticationResponse loginUsernameForBuyer(UsernameLoginRequest request, HttpHeaders headers);

    AuthenticationResponse loginOauth2ForSeller(Oauth2LoginRequest request, HttpHeaders headers);

    AuthenticationResponse loginOauth2ForBuyer(Oauth2LoginRequest request, HttpHeaders headers);

    AuthenticationResponse signupUsernameForSeller(UsernameLoginRequest request, HttpHeaders headers);

    AuthenticationResponse signupUsernameForBuyer(UsernameLoginRequest request, HttpHeaders headers);

    AuthenticationResponse signupOauth2ForSeller(Oauth2LoginRequest request, HttpHeaders headers);

    AuthenticationResponse signupOauth2ForBuyer(Oauth2LoginRequest request, HttpHeaders headers);

    AuthenticationResponse refreshForAdmin(String refreshToken, HttpHeaders headers);

    AuthenticationResponse refreshForSeller(String refreshToken, HttpHeaders headers);

    AuthenticationResponse refreshForBuyer(String refreshToken, HttpHeaders headers);

    void logoutForAdmin(HttpHeaders headers);

    void logoutForSeller(HttpHeaders headers);

    void logoutForBuyer(HttpHeaders headers);
}
