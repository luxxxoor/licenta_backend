package com.licenta.usm.Feign;

import com.licenta.emm.Managerment.IEmailManagerment;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "EMAIL-MANAGERMENT")
public interface EmailProxy extends IEmailManagerment {
}
