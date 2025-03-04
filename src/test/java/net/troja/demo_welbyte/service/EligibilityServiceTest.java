package net.troja.demo_welbyte.service;

import net.troja.demo_welbyte.beans.Eligibility;
import net.troja.demo_welbyte.beans.MemberStatus;
import net.troja.demo_welbyte.mapper.EligibilityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EligibilityServiceTest {
    public static final long MEMBER_ID = 123L;
    private static final String FIRST_NAME = "Donald";
    private static final String LAST_NAME = "Duck";
    @Mock
    private EligibilityMapper mapper;

    @InjectMocks
    private EligibilityService eligibilityService;

    @Test
    void checkEmployee() {
        LocalDate now = LocalDate.now();
        when(mapper.findByEmployee(MEMBER_ID, now)).thenReturn(mock(Eligibility.class));
        Eligibility eligibility =
                eligibilityService.getEligibility(MemberStatus.EMPLOYEE, MEMBER_ID, now, null, null);

        assertThat(eligibility).isNotNull();
        verify(mapper, times(1)).findByEmployee(MEMBER_ID, now);
    }

    @Test
    void checkDependent() {
        LocalDate now = LocalDate.now();
        when(mapper.findByDependent(FIRST_NAME, LAST_NAME, now)).thenReturn(mock(Eligibility.class));
        Eligibility eligibility =
                eligibilityService.getEligibility(MemberStatus.DEPENDENT, MEMBER_ID, now, FIRST_NAME, LAST_NAME);

        assertThat(eligibility).isNotNull();
        verify(mapper, times(1)).findByDependent(FIRST_NAME, LAST_NAME, now);
    }
}