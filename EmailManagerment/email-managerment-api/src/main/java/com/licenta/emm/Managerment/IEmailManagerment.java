package com.licenta.emm.Managerment;

import com.licenta.emm.Entities.EmailDTO;
import com.licenta.emm.Exceptions.EmailAlreadyInUse;
import com.licenta.emm.Exceptions.EmailNotSent;
import com.licenta.emm.Exceptions.InvalidConfirmationLink;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.licenta.emm.Managerment.Constants.EmailManagermentConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public interface IEmailManagerment {
    @RequestMapping(value = PATH + "/login", method = POST)
    ResponseEntity<Void> addEmailToUser(@RequestBody final EmailDTO emailDTO) throws EmailAlreadyInUse, EmailNotSent;
    @RequestMapping(value = PATH + "/confirm/", method = GET)
    ResponseEntity<Void> confirmEmail(@RequestParam(name="token") final String confirmationLink) throws InvalidConfirmationLink;
}
