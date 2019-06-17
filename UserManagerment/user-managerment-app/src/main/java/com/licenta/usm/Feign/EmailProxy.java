package com.licenta.usm.Feign;

import com.licenta.emm.Management.IEmailManagement;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "EMAIL-MANAGEMENT")
public interface EmailProxy extends IEmailManagement {
}
