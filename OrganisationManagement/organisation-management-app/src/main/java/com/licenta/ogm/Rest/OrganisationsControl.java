package com.licenta.ogm.Rest;

import com.licenta.ogm.Entities.Organisation;
import com.licenta.ogm.Organisation.IOrganisations;
import com.licenta.ogm.Service.OrganisationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class OrganisationsControl implements IOrganisations {
    private final OrganisationsService organisationsService;

    @Autowired
    public OrganisationsControl(OrganisationsService organisationsService) {
        this.organisationsService = organisationsService;
    }

    @Override
    public ResponseEntity<List<Organisation>> getOrganisationsForUserId(@RequestHeader("userId") final Integer userId) {
        final List<Organisation> organisations = organisationsService.findOrganisationsForUserId(userId);
        return new ResponseEntity<>(organisations, OK);
    }

    @Override
    public ResponseEntity<List<Organisation>> getAllSortedByPopularity(@RequestHeader("userId") final Integer userId) {
        final List<Organisation> organisations = organisationsService.getAllSortedByPopularity(userId);
        return new ResponseEntity<>(organisations, OK);
    }
}
