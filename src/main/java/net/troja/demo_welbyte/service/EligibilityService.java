package net.troja.demo_welbyte.service;

import lombok.RequiredArgsConstructor;
import net.troja.demo_welbyte.beans.Eligibility;
import net.troja.demo_welbyte.beans.MemberStatus;
import net.troja.demo_welbyte.mapper.EligibilityMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EligibilityService {
    private final EligibilityMapper eligibilityMapper;


    public Eligibility getEligibility(final MemberStatus memberStatus,
                                      final Long employeeId,
                                      final LocalDate employeeDateOfBirth,
                                      final String employeeFirstName,
                                      final String employeeLastName) {
        if(memberStatus == MemberStatus.DEPENDENT) {
            return eligibilityMapper.findByDependent(employeeFirstName, employeeLastName, employeeDateOfBirth);
        } else {
            return eligibilityMapper.findByEmployee(employeeId, employeeDateOfBirth);
        }
    }
}
