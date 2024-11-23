package cm.clock.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;



import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id;

    @NotEmpty(groups = {Register.class})
    private String name;

    @NotNull(groups = {Register.class, Login.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String studentId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String nickname;

    @NotNull(groups = {Register.class, Login.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String photo;

    private long time;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer status;

    public interface Register{}
    public interface Login{}

}


