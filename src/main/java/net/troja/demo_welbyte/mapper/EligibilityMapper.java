package net.troja.demo_welbyte.mapper;

import net.troja.demo_welbyte.beans.Eligibility;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EligibilityMapper {
    @Select("SELECT * FROM eligibility_records")
    List<Eligibility> findAll();

    @Select("SELECT * FROM eligibility_records WHERE member_unique_id = #{memberId} AND date_of_birth = #{birthDate} AND eligibility_start_date <= date('now')")
    Eligibility findByEmployee(Long memberId, LocalDate birthDate);

    @Select("SELECT * FROM eligibility_records WHERE first_name = #{firstName} AND last_name = #{lastName} AND date_of_birth = #{birthDate} AND eligibility_start_date <= date('now')")
    Eligibility findByDependent(String firstName, String lastName, LocalDate birthDate);
}
