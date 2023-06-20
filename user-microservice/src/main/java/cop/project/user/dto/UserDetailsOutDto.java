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
public class UserDetailsOutDto{

    private String userName;

    private String address;

    private String emailId;

    private Long contactNumber;

    private boolean isBusiness;

    private String businessName;

    private List<Location> location;

    private Long businesscontactNumber;

    private String businessAddress;

    private String country;

    private String state;

    private Integer pincode;
}
