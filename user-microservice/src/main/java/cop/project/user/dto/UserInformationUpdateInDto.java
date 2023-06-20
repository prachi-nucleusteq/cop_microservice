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
public class UserInformationUpdateInDto {

    private String userName;

    private Long locationId;

    private String address;

    private String country;

    private String state;

    private Integer pincode;

    private String emailId;

    private Long contactNumber;

    private String gender;

    private String businessName;

    private Long businessContactNumber;

    private List<Location> location;
}
