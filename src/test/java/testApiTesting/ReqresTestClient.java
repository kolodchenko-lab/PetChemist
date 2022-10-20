package testApiTesting;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor

public class ReqresTestClient {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("createdAt")
    private long createdAt;

}
