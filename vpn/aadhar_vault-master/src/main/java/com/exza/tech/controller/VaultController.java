package com.exza.tech.controller;
import com.exza.tech.model.Entity.Applications;
import com.exza.tech.model.Entity.Clients;
import com.exza.tech.model.requestbody.*;
import com.exza.tech.model.responsebody.AadharResponse;
import com.exza.tech.model.responsebody.LoginResponse;
import com.exza.tech.model.responsebody.SuccessResponse;
import com.exza.tech.repository.ApplicationsRepo;
import com.exza.tech.repository.ClientRepo;
import com.exza.tech.repository.UserRepo;
import com.exza.tech.security.JwtValidator;
import com.exza.tech.services.*;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/vault")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class VaultController {
    @Autowired
    public UserRepo userRepo;

    @Autowired
    private ClientRepo ClientRepo;

    @Autowired
    private VaultService vaultService ;

    @Autowired
    private RequestedService requestedService ;

    @Autowired
    private JwtValidator jwtValidator;

    @Autowired
    private SecretService secretService;

    @Autowired
    private  ApplicationsRepo applicationsRepo ;

    @GetMapping("/")
    public String Welcome() {
        return "Welcome to Aadhar Vault";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("addMoreClients")
    public ResponseEntity<SuccessResponse>   moreClients(AddClient clients)  {
        SuccessResponse res = new SuccessResponse();
        if(clients.getClientName()!=null || clients.getApikey() !=null || clients.getYearsOfSubscription() != null){
            Date onDate = Helper.convertStringToDate("yyyy-MM-dd", LocalDate.now().toString());
            Date expDate = Helper.convertStringToDate("yyyy-MM-dd",LocalDate.now().plusYears(clients.getYearsOfSubscription()).toString());
            ClientRepo.save(new Clients(clients.getApikey(),clients.getClientName(),expDate,onDate,10));
            res.message = "App Added Successfully";
            res.code = HttpStatus.OK.value();
            return new ResponseEntity<>(res, HttpStatus.OK);
        }else {
            res.code = HttpStatus.NOT_FOUND.value();
            res.message = "Insufficent data Found ";
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("addApps")
    public ResponseEntity<SuccessResponse> addApps(@RequestBody AddApps addApps)  {
        SuccessResponse res = new SuccessResponse();
        try {
            if(addApps.getAppId()==null || addApps.getAppName() ==null || addApps.getClientCode() == null){
                res.code = HttpStatus.NOT_FOUND.value();
                res.message = "Does not Allow Empty data ";
                return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
            }
            Clients clients =null;
            try{
               Optional<Clients> clientcode = ClientRepo.findById(addApps.getClientCode());
                clients = clientcode.get();
           }
           catch(NullPointerException e){
               e.printStackTrace();
               res.code = HttpStatus.NOT_FOUND.value();
               res.message = "Client Code not Found ";
               return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
           }
           String private_key = "";
           String public_key = "";
            Applications ap  = applicationsRepo.findByAppId(addApps.getAppId());
            if(ap != null){
                res.code = HttpStatus.NOT_ACCEPTABLE.value();
                res.message = "Appid Already exist ";
                return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
            }
           applicationsRepo.save(new Applications(addApps.getAppId(), addApps.getAppName(), clients, public_key, private_key, "freeze", addApps.getDesc()));
       }catch (Exception e){
           e.printStackTrace();
           res.code = HttpStatus.NOT_FOUND.value();
           res.message = "unknown exception ";
           return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
       }
        res.message = "App Added Successfully";
        res.code = HttpStatus.OK.value();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("generatekeys/{appId}")
    public ResponseEntity<Object> generateKeys(@PathVariable String appId,  HttpServletResponse response)  {
        try {
            Applications ap  = applicationsRepo.findByAppId(appId);
            System.out.println(ap );
            Map<String, String> keys = getkeys();
            String publickey = keys.get("publickey");
            String privatekey = keys.get("privatekey");
            ap.setPrivate_key(privatekey);
            ap.setPublic_key(publickey);
            ap.setStatus("run");
            applicationsRepo.save(ap);
            File file =secretService.generateFile(privatekey);
            InputStreamResource isr =new InputStreamResource(new FileInputStream(file));
            response.setHeader("Content-Disposition","attachment ; filename="+ap.getAppName()+"-pvt.key");
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
            response.setHeader("Pragma","no-cache");
            return ResponseEntity.ok().contentLength(file.length()).contentType(MediaType.parseMediaType("appication/txt")).body(isr);
        }catch (Exception e){
            SuccessResponse res = new SuccessResponse();
            e.printStackTrace();
            res.code = HttpStatus.NOT_FOUND.value();
            res.message = "Invalid Appid";
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }
        public Map<String,String> getkeys() throws NoSuchAlgorithmException, IOException {
            return SecretService.generateWithRsa();
        }


    public ResponseEntity<Object> download(HttpServletResponse response) throws IOException {
        //this api downlads the private key for their use
        String  filename ="private.key";
        File file =new File(filename);
        InputStreamResource isr =new InputStreamResource(new FileInputStream(file));
        response.setHeader("Content-Disposition","attachment ; filename="+file.getName());
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        response.setHeader("Pragma","no-cache");
        return ResponseEntity.ok().contentLength(file.length()).contentType(MediaType.parseMediaType("appication/txt")).body(isr);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("login")
    public  ResponseEntity<LoginResponse>  login(@RequestBody Loginbody loginbody) {
        LoginResponse res = new LoginResponse();
        if(loginbody.getPassword()==null || loginbody.getCompanyCode() ==null || loginbody.getUsername() == null){
            res.status = HttpStatus.NOT_FOUND.value();
            res.message = "Does not Allow Empty data ";
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        //UserInfo userinfo = userRepo.findByUserId(loginbody.getUsername(),loginbody.getCompanyCode());
        Optional<Clients> clientopt = ClientRepo.findById(loginbody.getCompanyCode());
       Clients clients =  null;
       if (!clientopt.isPresent()) {
           res.status = HttpStatus.UNAUTHORIZED.value() ;
    res.message = "Client id not found , please try again.." ;
        return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);}
        clients = clientopt.get();
        boolean validation = loginbody.getUsername().equals(loginbody.getPassword());
        if (validation) {
            res.setClientCode(clients);
            res.setName(clients.getClientName()+"Admin");
            res.getSubscription_exp(clients.getSubscription_exp());
            res.message = "login Successfull";
            res.status = HttpStatus.OK.value();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            res.status = HttpStatus.UNAUTHORIZED.value();
            res.message = "Incorrect Password , please try again..";
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/saveAadhar")
        public Object saveAadhar(@RequestHeader(value = "Authorization") String header ) throws CertificateException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, MalformedClaimException, InvalidJwtException
        {
            // save aadhar number into the data base by encrypting it
            Map<String,String> headmap =new HashMap<>();
            headmap.put("Authorization",header);
            if (header == null || !header.startsWith("Token ")) {
                throw new RuntimeException("JWT Token is missing");
            }
            String authenticationToken = header.substring(6);
            // validates jwt token using rsa public key
            JwtUser jwtuser = jwtValidator.validate(authenticationToken);
            System.out.println(jwtuser.getAadharnumber());
            boolean valid = Verhoeff.ValidateVerhoeff(jwtuser.getAadharnumber());
            if(!valid){
                return "enter a valid Aadhar number";
            }
            try {
                return vaultService.Save(jwtuser);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAadhar")
        public Object getaadhar(@RequestHeader(value = "Authorization") String header ) throws IOException, CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidJwtException, MalformedClaimException {
            // using the Refrence number given for aadhar  it fetches aadhar and decrypt and return originl AAdhar number
            Map<String,String> headmap =new HashMap<>();
            headmap.put("Authorization",header);
            if (header == null || !header.startsWith("Token ")) {
                throw new RuntimeException("JWT Token is missing");
            }
            String authenticationToken = header.substring(6);
            System.out.println(authenticationToken);
            //validates jwt token
            RequestJWT requestjwt=  jwtValidator.requestvalidator(authenticationToken);

           // boolean eligibly =requestedService.eligibilityCheck(requestjwt.getMetadata().getEmailId());
            if(true){
                try {
                    AadharResponse resp =requestedService.getaadhar(requestjwt);
                    System.out.println(resp);
                    return resp  ;
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }}else{
                return "your Subscription has Expired";
            }
            return null;
        }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllClients")
public Iterable<Clients> getAllClients(){
   return ClientRepo.findAll();
}
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/removApps/{appid}")
    public ResponseEntity<LoginResponse> removeApps(@PathVariable String appid){

        LoginResponse res = new LoginResponse();
        Applications app =applicationsRepo.findByAppId(appid);
                    try {applicationsRepo.delete(app);
                        res.message = app.getAppId()+" App removed Successfully";
                        res.status = HttpStatus.OK.value();
                        return new ResponseEntity<>(res, HttpStatus.OK);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        res.status = HttpStatus.NOT_FOUND.value();
                        res.message = "Appid not found  ";
                        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
                    }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllApps/{client}")
    public  Iterable<Applications> getAllApps(@PathVariable Long client){
        List<Applications> apps =null ;
        List<Applications> applications =new ArrayList<Applications>() ;
        Optional<Clients> clients = ClientRepo.findById(client);
        if (clients.isPresent()){
            apps =   applicationsRepo.findAllApp(clients.get());
        }
        if(apps != null){
            for(Applications app: apps){
                app.setPublic_key("");
                app.setPrivate_key("");
                applications.add(app);
            }

        }


        return  applications ;
    }


}