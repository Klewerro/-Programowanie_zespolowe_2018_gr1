package mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pojo.Address;
import pojo.Doctor;
import pojo.DoctorWorkingDays;
import pojo.Patient;

import java.util.List;

@Mapper
public interface DoctorModuleMapper {


    @Select("select id_doctor, first_name, last_name, from doctors where id_doctor=#{doctorId}")
    @Results({
            @Result(property = "doctorId", column = "id_doctor"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")
    })
    Doctor getDoctor(int doctorId);

    @Select("select day, hour_from, hour_to, hour_interval from doctorworkingdays where id_doctor=#{doctorId}")
    @Results({
            @Result(property = "doctorId", column = "id_doctor"),
            @Result(property = "day", column = "day"),
            @Result(property = "hourFrom", column = "hour_from"),
            @Result(property = "hourTo", column = "hour_to"),
            @Result(property = "hourInterval", column = "hour_interval")
    })
    List<DoctorWorkingDays> getDoctorWorkingDays(int doctorId);


    @Insert("INSERT into doctorworkingdays(id_doctor_working_day, id_doctor, day, hour_from, hour_to, hour_interval) VALUES (#{doctorWorkingDayId}, #{doctorId}, #{day}, #{hourFrom}, #{hourTo}," +
            "#{hourInterval})")
    @Options(useGeneratedKeys = true, keyProperty = "doctorId", keyColumn = "id_doctor")
    void addDoctorWorkingDays(DoctorWorkingDays doctorWorkingDays);

    @Update("UPDATE doctorworkingdays SET hour_from=#{hourFrom}, hour_to=#{hourTo}, hour_interval=#{hourInterval} WHERE id_doctor=#{doctorId} AND day=#{day}")
    void updateDoctorWorkingDays(DoctorWorkingDays doctorWorkingDays);

}