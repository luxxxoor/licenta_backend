package com.licenta.ath.Feign;

import com.licenta.usm.Management.IUserManagement;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "USER-MANAGERMENT-MANAGEMENT")
public interface UserManagementProxy extends IUserManagement {
}
