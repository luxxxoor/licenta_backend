package com.licenta.ath.Feign;

import com.licenta.usm.Managerment.IUserManagerment;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "USER-MANAGERMENT")
public interface UserProxy extends IUserManagerment {
}
