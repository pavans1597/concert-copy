package com.exza.tech.security;

import com.exza.tech.model.requestbody.JwtUser;
import com.exza.tech.model.requestbody.RequestJWT;
import com.exza.tech.repository.ApplicationsRepo;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.stereotype.Component;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class JwtGenerator {

    @Autowired
    public ApplicationsRepo applicationsRepo;


    public String generate(JwtUser jwtUser) throws Exception {
        PrivateKey key= getPrivateKey(jwtUser.getAppid());
        LocalDateTime ldt = LocalDateTime.now();
        Date d = Date.from(ldt.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant());
        Map<String,Object> claims= new HashMap<>() ;
        claims.put("appid",jwtUser.getAppid());
        claims.put("aadharnumber", jwtUser.getAadharnumber());
        claims.put("metadata", jwtUser.getMetadata());
        String token = Jwts.builder().setHeaderParam("alg","RS256").setHeaderParam("typ","JWT").setClaims(claims).signWith(SignatureAlgorithm.RS256,key).compact();
        return token; }

    public String adharRequestJWT(RequestJWT jwtrequest) throws Exception {
        PrivateKey key= getPrivateKey(jwtrequest.getAppId());
        LocalDateTime ldt = LocalDateTime.now();
        Date d = Date.from(ldt.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant());
        Map<String,Object> claims= new HashMap<>() ;
        claims.put("appid",jwtrequest.getAppId());
        claims.put("aadharnumber", jwtrequest.getrefuuid());
        claims.put("metadata", jwtrequest.getMetadata());
        String token = Jwts.builder().setHeaderParam("alg","RS256").setHeaderParam("typ","JWT").setClaims(claims).signWith(SignatureAlgorithm.RS256,key).compact();
        return token;
    }

   public PrivateKey getPrivateKey(String appId) throws Exception {
        //TODO - This below line must be removed and the private Key are bought from HSM
    //   String keys = userRepo.findByEmailId(UserId).getPrivate_key();
       String keys ="";
      try {  keys = applicationsRepo.findByAppId(appId).getPrivate_key() ;}
      catch (NullPointerException e){
      throw new Exception("AppId not Registered or Keys Not Generated ");
      }
      keys=keys.replaceAll("-----BEGIN PRIVATE KEY-----","");
        keys=keys.replaceAll("-----END PRIVATE KEY-----","");
        byte [] encoded = Base64.decode(keys);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey key = kf.generatePrivate(keySpec);
        return key ;
    }

}