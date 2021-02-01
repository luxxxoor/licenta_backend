package com.licenta.ogm.UserSubscription;

import com.licenta.ogm.Entities.Organisation;
import com.licenta.ogm.Exceptions.AlreadyExistingSubscriptionException;
import com.licenta.ogm.Exceptions.OrganisationNotFoundException;
import com.licenta.ogm.Exceptions.SubscriptionNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.licenta.ogm.UserSubscription.Constants.UserSubscriptionConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.*;

public interface IUserSubscription {
    @RequestMapping(value = PATH, method = PUT)
    ResponseEntity<Void> subscribe(@RequestHeader("userId") final Integer userId,
                                   @RequestParam("organisationId") final Integer organisationId)
            throws OrganisationNotFoundException, AlreadyExistingSubscriptionException;

    @RequestMapping(value = PATH, method = DELETE)
    ResponseEntity<Void> unsubscribe(@RequestHeader("userId") final Integer userId,
                                     @RequestParam("organisationId") final Integer organisationId)
            throws SubscriptionNotFoundException;

    @RequestMapping(value = PATH + "/getUserSubscriptions", method = POST)
    ResponseEntity<List<Organisation>>  getUserSubscriptions(@RequestHeader("userId") final Integer userId);
}
