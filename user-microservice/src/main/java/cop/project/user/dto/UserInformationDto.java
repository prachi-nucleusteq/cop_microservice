package cop.project.user.dto;

import java.util.List;

import cop.project.user.dbo.Location;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserInformationDto {

    private String userName;

    private String address;

    private String country;

    private String state;

    private Integer pincode;

    private String emailId;

    private String password;

    private Long contactNumber;

    private Boolean isBusiness;

    private String gender;

    private String accountStatus;

    private String businessName;

    private Long businessContactNumber;

    private List<Location> location;
}
