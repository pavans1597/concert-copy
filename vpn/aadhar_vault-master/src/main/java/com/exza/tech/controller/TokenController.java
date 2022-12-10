package com.exza.tech.controller;
import com.exza.tech.model.requestbody.JwtUser;
import com.exza.tech.model.requestbody.RequestJWT;
import com.exza.tech.model.responsebody.SuccessResponse;
import com.exza.tech.security.JwtGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/token")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class TokenController {

    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/save")
    public Object generate(@RequestBody JwtUser jwtUser) throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, InvalidKeySpecException {
        try {
            return jwtGenerator.generate(jwtUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new SuccessResponse(400,e.getMessage());
        }
    }

    @PostMapping("/request")
    public Object reqTokengenerate (@RequestBody RequestJWT requestJWT) {
        System.out.println(requestJWT);
        try {
            return jwtGenerator.adharRequestJWT(requestJWT);
        } catch (Exception e) {
            e.printStackTrace();
            return new SuccessResponse(400,e.getMessage());
        }
    }



}