package com.licenta.ogm.Admin;

import com.licenta.ogm.Entities.Organisation;
import com.licenta.ogm.Exceptions.AlreadyExistingOrganisationException;
import com.licenta.ogm.Exceptions.InvalidPasswordException;
import com.licenta.ogm.Exceptions.OrganisationNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.licenta.ogm.Admin.Constants.OrganisationAdminConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public interface IOrganisationAdmin {
    @RequestMapping(value = PATH + "/organisation", method = GET)
    ResponseEntity<Organisation> getOrganisation(@RequestParam("id") final Integer id) throws OrganisationNotFoundException;

    @RequestMapping(value = PATH + "/organisation", method = POST)
    ResponseEntity<Void> addOrganisation(@RequestParam("name") final String name,
                                         @RequestParam("password") final String password) throws AlreadyExistingOrganisationException, InvalidPasswordException;
}
