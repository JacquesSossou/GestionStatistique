package com.rtr.auth;



import com.rtr.model.User;
import com.rtr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService service;

    private final UserRepository repository;

    @GetMapping("/test")
    public String find() {
        return "Hello world";
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        if (repository.existsByEmail(request.getEmail())) {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setMesssage("Email a déja été utilisé");
            return ResponseEntity.badRequest().body(new AuthenticationResponse(("Email a déja été utilisé")));
        } else {
            return ResponseEntity.ok(service.register(request));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        User user = repository.findByEmail(request.getEmail()).get();
        if(user.getActivated()!=1) {
            AuthenticationResponse error = new AuthenticationResponse("Votre compte est inactif! veuillez contacter l'administrateur");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(service.authenticate(request));
    }


}
