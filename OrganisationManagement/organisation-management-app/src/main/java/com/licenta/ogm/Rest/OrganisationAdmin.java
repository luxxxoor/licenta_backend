package com.licenta.ogm.Rest;

import com.licenta.ogm.Admin.IOrganisationAdmin;
import com.licenta.ogm.Entities.Organisation;
import com.licenta.ogm.Exceptions.AlreadyExistingOrganisationException;
import com.licenta.ogm.Exceptions.InvalidPasswordException;
import com.licenta.ogm.Exceptions.OrganisationNotFoundException;
import com.licenta.ogm.Service.OrganisationAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class OrganisationAdmin implements IOrganisationAdmin {
    private final OrganisationAdminService organisationAdminService;

    @Autowired
    public OrganisationAdmin(OrganisationAdminService organisationAdminService) {
        this.organisationAdminService = organisationAdminService;
    }

    @Override
    public ResponseEntity<Organisation> getOrganisation(@RequestParam("id") final Integer id)
            throws OrganisationNotFoundException {
        final Organisation organisation = organisationAdminService.findOrganisationById(id);
        return new ResponseEntity<>(organisation, OK);
    }

    @Override
    public ResponseEntity<Void> addOrganisation(@RequestParam("name") final String name,
                                                @RequestParam("password") final String password)
            throws AlreadyExistingOrganisationException, InvalidPasswordException {
        organisationAdminService.addOrganisation(name, password);
        return new ResponseEntity<>(OK);
    }
}
