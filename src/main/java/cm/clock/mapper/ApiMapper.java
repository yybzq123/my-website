package cm.clock.mapper;

import cm.clock.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApiMapper {

    @Select("select * from students" )
    List<User> list();


}
