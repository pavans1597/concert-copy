package com.exza.tech.services;

import com.exza.tech.model.Entity.Secured;
import com.exza.tech.model.Entity.Vault;
import com.exza.tech.model.requestbody.JwtUser;
import com.exza.tech.model.responsebody.JwtResponse;
import com.exza.tech.repository.VaultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class VaultService {

    @Autowired
    private VaultRepo vaultRepo;

    @Value("${aes.secret}")
    private String SECRET ;

    @Transactional
    public JwtResponse Save(JwtUser user) throws NoSuchAlgorithmException {
        Secured secured =new Secured();
        SecretService sc = new SecretService();
        Map<String,String>  map  =sc.encryptMyKey(SECRET);
        Map.Entry<String,String> entry = map.entrySet().iterator().next();
        String salt = entry.getKey();
        String hash = entry.getValue();
        secured.setEncrypteddata(sc.encryptAadahr(user.getAadharnumber(),hash));
        String time =LocalDateTime.now().toString();
        String uuid = UUID.randomUUID().toString();
        secured.setAadhaarUuid(uuid);
        Vault vault = new Vault( user.getAppid(), salt,time,user.getMetadata().getEmpId(),uuid);
        vault.setReference(secured);
        vaultRepo.save(vault);
        return new JwtResponse(user.getAppid(),secured.getAadhaarUuid());
    }

}