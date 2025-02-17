package com.licenta.ogm.Organisation;

import com.licenta.ogm.Entities.Organisation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.licenta.ogm.Organisation.Constants.OrganisationsConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

public interface IOrganisations {
    @RequestMapping(value = PATH + "/getByUserId", method = GET)
    ResponseEntity<List<Organisation>> getOrganisationsForUserId(@RequestHeader("userId") final Integer userId);

    @RequestMapping(value = PATH + "/getAllSortedByPopularity", method = GET)
    ResponseEntity<List<Organisation>> getAllSortedByPopularity(@RequestHeader("userId") final Integer userId);
}
