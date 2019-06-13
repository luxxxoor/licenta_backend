package com.licenta.ogm.Rest;

import com.licenta.ogm.Exceptions.AlreadyExistingSubscriptionException;
import com.licenta.ogm.Exceptions.OrganisationNotFoundException;
import com.licenta.ogm.Exceptions.SubscriptionNotFoundException;
import com.licenta.ogm.Service.UserSubscriptionService;
import com.licenta.ogm.UserSubscription.IUserSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class UserSubscription implements IUserSubscription {
    private final UserSubscriptionService userSubscriptionService;

    @Autowired
    public UserSubscription(UserSubscriptionService userSubscriptionService) {
        this.userSubscriptionService = userSubscriptionService;
    }

    @Override
    public ResponseEntity<Void> subscribe(@RequestParam("userId") final Integer userId,
                                          @RequestParam("organisationId") final Integer organisationId)
            throws OrganisationNotFoundException, AlreadyExistingSubscriptionException {
        userSubscriptionService.subscribe(userId, organisationId);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<Void> unsubscribe(@RequestParam("userId") final Integer userId,
                                            @RequestParam("organisationId") final Integer organisationId)
            throws SubscriptionNotFoundException {
        userSubscriptionService.unsubscribe(userId, organisationId);
        return new ResponseEntity<>(OK);
    }
}
