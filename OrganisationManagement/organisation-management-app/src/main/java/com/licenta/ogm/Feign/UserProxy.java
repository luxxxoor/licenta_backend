package com.licenta.ogm.Feign;

import com.licenta.usm.Management.IUserManagement;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "USER-MANAGERMENT")
public interface UserProxy extends IUserManagement {
}
