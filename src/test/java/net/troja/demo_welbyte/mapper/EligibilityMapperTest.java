package net.troja.demo_welbyte.mapper;

import net.troja.demo_welbyte.beans.Eligibility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EligibilityMapperTest {
    private static final long MEMBER_ID_WALTER = 44000100L;
    private static final long MEMBER_ID_DONALD = 44000112L;
    private static final LocalDate BIRTH_DATE_WALTER = LocalDate.of(1979, 1, 9);
    private static final LocalDate BIRTH_DATE_DONALD = LocalDate.of(1942, 2, 9);

    @Autowired
    private EligibilityMapper eligibilityMapper;

    @Test
    void findAll() {
        List<Eligibility> eligibilities = eligibilityMapper.findAll();

        assertThat(eligibilities).hasSize(2);
        System.out.println(eligibilities);
    }

    @Test
    void findByEmployee() {
        Eligibility employee = eligibilityMapper.findByEmployee(MEMBER_ID_WALTER, BIRTH_DATE_WALTER);

        assertThat(employee).isNotNull();
        assertThat(employee.getMemberUniqueId()).isEqualTo(MEMBER_ID_WALTER);
    }

    @Test
    void findByEmployee_notEligible() {
        Eligibility employee = eligibilityMapper.findByEmployee(MEMBER_ID_DONALD, BIRTH_DATE_DONALD);

        assertThat(employee).isNull();
    }

    @Test
    void findByDependent() {
        Eligibility employee = eligibilityMapper.findByDependent("Walter", "Jacobson", BIRTH_DATE_WALTER);

        assertThat(employee).isNotNull();
        assertThat(employee.getMemberUniqueId()).isEqualTo(MEMBER_ID_WALTER);
    }

    @Test
    void findByDependent_notEligible() {
        Eligibility employee = eligibilityMapper.findByDependent("Donald", "Duck", BIRTH_DATE_DONALD);

        assertThat(employee).isNull();
    }
}