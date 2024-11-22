package cm.clock.mapper;

import cm.clock.pojo.DTO.updatePwdDTO;
import cm.clock.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Insert("insert into students(student_id,password,name) " +
            "values (#{studentId},#{password},#{name})")
    public void appregister(String studentId,String password,String name  ) ;


    @Select("select * from students where student_id=#{studentId}")
    User findByUserName(String studentId);

    @Update("update students set password=#{newPwd} where student_id=#{studentId}")
    void updatePwd(updatePwdDTO forgetPwdDTO);
}
