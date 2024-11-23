package cm.clock.pojo.DTO;

import lombok.Data;

// 忘记密码传输对象
@Data
public class updatePwdDTO {
    private String name;
    private String studentId;
    private String newPwd;
    private String reNewPwd;
}
