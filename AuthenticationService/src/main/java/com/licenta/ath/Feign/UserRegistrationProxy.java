package com.licenta.ath.Feign;

import com.licenta.usm.Registration.IUserRegistration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "USER-MANAGERMENT-REGISTRATION")
public interface UserRegistrationProxy extends IUserRegistration {
}
