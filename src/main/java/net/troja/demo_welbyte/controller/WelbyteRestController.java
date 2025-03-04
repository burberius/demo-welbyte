package net.troja.demo_welbyte.controller;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import net.troja.demo_welbyte.beans.Eligibility;
import net.troja.demo_welbyte.beans.MemberStatus;
import net.troja.demo_welbyte.service.EligibilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("eligibility")
@Validated
@RequiredArgsConstructor
public class WelbyteRestController {
    private final EligibilityService eligibilityService;

    @GetMapping
    public ResponseEntity<Eligibility> eligibility(@RequestParam(name = "employee_code") @NotBlank(message = "employee_code is required") String employeeCode,
                                                   @RequestParam(name = "member_status") @NotNull(message = "member_status is required") MemberStatus memberStatus,
                                                   @RequestParam(name = "employee_id") @Nullable Long employeeId,
                                                   @RequestParam(name = "employee_date_of_birth") @NotNull(message = "employee_date_of_birth is required") LocalDate employeeDateOfBirth,
                                                   @RequestParam(name = "employee_first_name") @Nullable String employeeFirstName,
                                                   @RequestParam(name = "employee_last_name") @Nullable String employeeLastName)
            throws MissingServletRequestParameterException {
        if (memberStatus == MemberStatus.EMPLOYEE && employeeId == null) {
            throw new MissingServletRequestParameterException("employee_id", "String");
        }
        String nameFieldBlank = whichNameFieldIsBlank(employeeFirstName, employeeLastName);
        if (memberStatus == MemberStatus.DEPENDENT && nameFieldBlank != null) {
            throw new MissingServletRequestParameterException(nameFieldBlank, "String");
        }

        Eligibility eligibility = eligibilityService.getEligibility(memberStatus, employeeId, employeeDateOfBirth, employeeFirstName, employeeLastName);

        if (eligibility == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(eligibility);
        }
    }

    private String whichNameFieldIsBlank(final String employeeFirstName, final String employeeLastName) {
        if(StringUtils.isBlank(employeeFirstName)) {
            return "employee_first_name";
        }
        if(StringUtils.isBlank(employeeLastName)) {
            return "employee_last_name";
        }
        return null;
    }
}
