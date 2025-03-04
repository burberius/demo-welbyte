package net.troja.demo_welbyte.beans;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Eligibility {
    private long memberUniqueId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String relations;
    private String subGroup;
    private String jobType;
    private LocalDate hireDate;
    private LocalDate eligibilityStartDate;
    private LocalDate eligibilityEndDate;
    private String employeeStatus;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String city;
    private String stateCode;
    private String zipCode;
    private String country;
    private String employeeGroup;
}
