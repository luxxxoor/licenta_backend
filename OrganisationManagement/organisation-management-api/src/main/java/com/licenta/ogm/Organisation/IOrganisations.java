package com.licenta.ogm.Organisation;

import com.licenta.ogm.Entities.Organisation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.licenta.ogm.Organisation.Constants.OrganisationsConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

public interface IOrganisations {
    @RequestMapping(value = PATH + "/getByUserId", method = GET)
    ResponseEntity<List<Organisation>> getOrganisationsForUserId(@RequestParam("userId") final Integer userId);

    @RequestMapping(value = PATH + "/getAll", method = GET)
    ResponseEntity<List<Organisation>> getAll();
}
