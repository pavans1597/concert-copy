package com.exza.tech.controller;


import com.exza.tech.model.Entity.Applications;
import com.exza.tech.model.Entity.Clients;
import com.exza.tech.model.Entity.Requested;
import com.exza.tech.repository.ClientRepo;
import com.exza.tech.repository.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/token")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class LogController {

    @Autowired
    private ClientRepo ClientRepo;

    @Autowired
    private RequestRepo requestRepo ;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("getAppLogs/{client}/{appId}")
    public List<Requested> getAppLogs(@PathVariable Long client ,@PathVariable String appId)   {
       Clients clients =getclient(client);
       if(clients != null ) {
            return requestRepo.findByAppId(appId,ClientRepo.findById(client).get());
        }
       return new ArrayList<>();
    }

    private Clients getclient(Long client) {
        Optional<Clients> clients = ClientRepo.findById(client);
        if(clients.isPresent()){
            return clients.get();
        }
        return  null ;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("getEmpLogs/{client}/{empId}")
    public List<Requested> getEmpLogs(@PathVariable Long client,@PathVariable String empId)   {
        Clients clients =getclient(client);
        if(clients != null ) {
        return requestRepo.findByEmpId(empId, ClientRepo.findById(client).get());
    }
        return new ArrayList<>();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("searchEmp/{client}/{empId}")
    public List<Requested> searchEmployee(@PathVariable Long client,@PathVariable String empId) {
        Clients clients =getclient(client);
        if(clients != null ) {    return requestRepo.searchEmp(empId, ClientRepo.findById(client).get());
    }
           return new ArrayList<>();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("searchApp/{client}/{appId}")
    public List<Requested> searchapp(@PathVariable Long client,@PathVariable String appId)   {
        Clients clients =getclient(client);
        if(clients != null ) {
            return requestRepo.searchApp(appId, ClientRepo.findById(client).get());
            }
        return new ArrayList<>();
        }

}
