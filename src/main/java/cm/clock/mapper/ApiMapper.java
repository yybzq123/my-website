package cm.clock.mapper;

import cm.clock.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ApiMapper {

    @Select("select * from students" )
    List<User> list();


    @Insert("insert into clock values (null,#{studentId},now()) ")
    void clockIn(String studentId);

    @Select("select start_time from clock where student_id=#{studentId} ")
    LocalDateTime getStartTime(String studentId);

}
