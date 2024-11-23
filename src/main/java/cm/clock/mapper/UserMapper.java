package cm.clock.mapper;

import cm.clock.pojo.DTO.updatePwdDTO;
import cm.clock.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {



    @Insert("insert into students(student_id,password,name) " +
            "values (#{studentId},#{password},#{name})")
    public void appregister(String studentId,String password,String name  ) ;


    @Select("select * from students where student_id=#{studentId}")
    User findByUserName(String studentId);

    @Update("update students set password=#{newPwd} where student_id=#{studentId}")
    void updatePwd(updatePwdDTO forgetPwdDTO);

    @Select("select * from students where student_id=#{studentId}")
    User getUserInfo(String studentId);

    @Select("SELECT COUNT(*) FROM clock WHERE student_id = #{studentId}")
    int countByStudentId(String studentId);

    @Update("update students set time=#{time} where student_id=#{studentId}")
    void updateTime(long time, String studentId);

    @Delete("delete from clock where student_id=#{studentId}")
    void deleteClock(String studentId);
}
