package com.exza.tech.services;

import com.exza.tech.model.Entity.*;
import com.exza.tech.model.requestbody.RequestJWT;
import com.exza.tech.model.responsebody.AadharResponse;
import com.exza.tech.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;


@Service
public class RequestedService {

    @Value("${aes.secret}")
    private String SECRET ;


    @Autowired
    private  ApplicationsRepo applicationsRepo ;
    @Autowired
    private UserRepo userRepo ;
    @Autowired
    private ClientRepo clientRepo ;
    @Autowired
    private RequestRepo requestRepo ;
    @Autowired
    private VaultRepo vaultRepo;
    @Autowired
    private SecuredRepo securedRepo;

    @Transactional
    public AadharResponse getaadhar(RequestJWT request) throws NoSuchAlgorithmException {
        System.out.println(request);
        //Vault vault = vaultDAO.getaadhar(request);
        Vault vault = vaultRepo.getaadhar(request.getrefuuid());
        System.out.println(vault);
        SecretService sc = new SecretService();
        Secured  securedaadhar = vault.getSecured();
        String Aeskey =   sc.decryptMyKey(vault.getSalt(),SECRET);
        String decryptAadhar = sc.decryptAadhar(securedaadhar.getEncrypteddata(),Aeskey);
        String time =LocalDateTime.now().toString();
        Requested requested = new Requested();
        Clients client =applicationsRepo.findByAppId(request.getAppId()).getClientCode();
        requested.setAADAHRID(request.getrefuuid());
        System.out.println(requested.getAADAHRID());
        requested.setAppId(request.getAppId());
        requested.setTIME(time);
        requested.setClientCode(client);
        requested.setEMPLOYEEID(request.getMetadata().getEmpId());
        requested.setEMPLOYEENAME(request.getMetadata().getEmpName());
        requestRepo.save(requested);
        return new AadharResponse(request.getAppId(),decryptAadhar);}



}
