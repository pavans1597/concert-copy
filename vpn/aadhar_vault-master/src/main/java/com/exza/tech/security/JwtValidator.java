package com.exza.tech.security;

import com.exza.tech.model.requestbody.JwtUser;
import com.exza.tech.model.requestbody.Metadata;
import com.exza.tech.model.requestbody.RequestJWT;
import com.exza.tech.repository.ApplicationsRepo;
import com.exza.tech.repository.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class JwtValidator {

    @Autowired
    public ApplicationsRepo applicationsRepo;

    JwtConsumer jwtConsumer = new JwtConsumerBuilder().setSkipSignatureVerification().build();

    public JwtUser validate(String token) throws IOException, CertificateException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidJwtException, MalformedClaimException {
        JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
        String appId =  jwtClaims.getClaimValue("appid", String.class);
        PublicKey key = getkeys(appId);
        Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        JwtUser jwtUser = new JwtUser();
        Map<String,String> map = (Map<String, String>) body.get("metadata");
        Metadata meta =new Metadata();
        meta.setEmpId( map.get("empId"));
        meta.setEmpName(map.get("empName"));
        jwtUser.setMetadata( meta);
        jwtUser.setAppid((String) body.get("appid"));
        jwtUser.setAadharnumber((String) body.get("aadharnumber"));
        return jwtUser;
    }

    public RequestJWT requestvalidator(String token) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidJwtException, MalformedClaimException {
        JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
        String appId =  jwtClaims.getClaimValue("appid", String.class);
        PublicKey key = getkeys(appId);
        Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        RequestJWT requestJWT = new RequestJWT();
        Map<String,String> map = (Map<String, String>) body.get("metadata");
        Metadata meta =new Metadata();
        meta.setEmpId( map.get("empId"));
        meta.setEmpName(map.get("empName"));
        requestJWT.setMetadata(meta);
        requestJWT.setrefuuid((String) body.get("aadharnumber"));
        requestJWT.setAppId((String) body.get("appid"));
        return requestJWT;
    }

  public PublicKey getkeys(String appId) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //TODO - This below line must be removed and the public Key are bought from HSM
      String keys = applicationsRepo.findByAppId(appId).getPublic_key() ;
        keys=keys.replaceAll("-----BEGIN PUBLIC KEY-----","");
        keys=keys.replaceAll("-----END PUBLIC KEY-----","");
        keys=keys.replaceAll(" ","");
        System.out.println(keys);
      byte [] encoded = Base64.getDecoder().decode(keys);
      //byte [] encoded = Base64.decode(keys);
        X509EncodedKeySpec spec = new  X509EncodedKeySpec(encoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey key = kf.generatePublic(spec);
        return  key ;
        //30f158e2-7cd9-492f-85cc-51a204303298
      //30f158e2-7cd9-492f-85cc-51a204303298
    }
    
}
