package cm.clock.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String student_id;
    private String nickname;
    private String password;
    private String photo;
    private BigDecimal time;
    private Integer status;
}
