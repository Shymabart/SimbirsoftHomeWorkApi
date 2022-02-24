package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties (ignoreUnknown = true)
public class UserPojos{
    private int id;

    /*public String getEmail() {
        return email;
    }*/

    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

}
