package cm.clock.mapper;

import cm.clock.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface UserMapper {

    @Insert("insert into students(student_id,password,name) " +
            "values (#{studentId},#{password},#{name})")
    public void appregister(String studentId,String password,String name  ) ;


    @Select("select * from students where name=#{name}")
    User findByUserName(String name);
}
