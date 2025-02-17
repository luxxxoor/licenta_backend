package com.licenta.emm.Rest;

import com.licenta.emm.Entities.EmailDTO;
import com.licenta.emm.Exceptions.EmailAlreadyInUse;
import com.licenta.emm.Exceptions.EmailNotSent;
import com.licenta.emm.Exceptions.InvalidConfirmationLink;
import com.licenta.emm.Management.IEmailManagement;
import com.licenta.emm.Service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class EmailManagement implements IEmailManagement {

    private final ManagementService managementService;

    @Autowired
    public EmailManagement(ManagementService managementService) {
        this.managementService = managementService;
    }

    @Override
    public ResponseEntity<Void> addEmailToUser(@RequestBody final EmailDTO emailDTO)
            throws EmailAlreadyInUse, EmailNotSent {
        managementService.addEmailToUser(emailDTO);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<Void> confirmEmail(@RequestParam(name = "token") final String confirmationLink)
            throws InvalidConfirmationLink {
        managementService.confirm(confirmationLink);
        return new ResponseEntity<>(OK);
    }
}
