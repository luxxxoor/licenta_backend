package com.licenta.ogm.UserSubscription;

import com.licenta.ogm.Exceptions.AlreadyExistingSubscriptionException;
import com.licenta.ogm.Exceptions.OrganisationNotFoundException;
import com.licenta.ogm.Exceptions.SubscriptionNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.licenta.ogm.UserSubscription.Constants.UserSubscriptionConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

public interface IUserSubscription {
    @RequestMapping(value = PATH, method = PUT)
    ResponseEntity<Void> subscribe(@RequestParam("userId") final Integer userId,
                                      @RequestParam("organisationId") final Integer organisationId)
            throws OrganisationNotFoundException, AlreadyExistingSubscriptionException;

    @RequestMapping(value = PATH, method = DELETE)
    ResponseEntity<Void> unsubscribe(@RequestParam("userId") final Integer userId,
                                     @RequestParam("organisationId") final Integer organisationId)
            throws SubscriptionNotFoundException;
}
