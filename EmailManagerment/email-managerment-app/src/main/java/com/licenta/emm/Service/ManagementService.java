package com.licenta.emm.Service;

import com.licenta.emm.Domain.ORM.ConfirmationLink;
import com.licenta.emm.Domain.ORM.Email;
import com.licenta.emm.Entities.EmailDTO;
import com.licenta.emm.Exceptions.EmailAlreadyInUse;
import com.licenta.emm.Exceptions.EmailNotSent;
import com.licenta.emm.Exceptions.InvalidConfirmationLink;
import com.licenta.emm.Repository.ConfirmationLinkRepository;
import com.licenta.emm.Repository.EmailRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ManagementService {
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private ConfirmationLinkRepository confirmationLinkRepository;
    @Autowired
    private SmtpService smtpService;

    public void addEmailToUser(final EmailDTO emailDTO) throws EmailAlreadyInUse, EmailNotSent {
        if (isEmailAvailable(emailDTO.getEmail())) {
            final Email email = new Email(emailDTO.getId(), emailDTO.getEmail());
            emailRepository.save(email);
            sendConfirmationLink(email);
        } else {
            throw new EmailAlreadyInUse();
        }
    }

    private boolean isEmailAvailable(final String email) {
        final Optional<Email> existingEmail = emailRepository.findByEmail(email);

        return existingEmail.isEmpty();
    }

    private void sendConfirmationLink(final Email email) throws EmailNotSent {
        final String randomLink = RandomStringUtils.randomAlphanumeric(6);
        final String link = "http://localhost:3000/confirmation/" + randomLink;
        smtpService.sendMail(email, link);

        final ConfirmationLink confirmationLink = new ConfirmationLink(email, randomLink);
        confirmationLinkRepository.save(confirmationLink);
    }

    public void confirm(final String confirmationLink) throws InvalidConfirmationLink {
        final Optional<ConfirmationLink> existingConfirmationLink =
                confirmationLinkRepository.findByLink(confirmationLink);

        if (existingConfirmationLink.isPresent()){
            final Email email = existingConfirmationLink.get().getEmail();
            email.setConfirmed(true);
            emailRepository.save(email);
        }
        throw new InvalidConfirmationLink();
    }
}
