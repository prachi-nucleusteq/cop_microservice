package cop.project.user.dbo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "business_id")
    private Long businessId;

    private String address;

    private String country;

    private String state;

    @Column(name = "pin_code")
    private Integer pincode;

    @Column(name = "email_id")
    private String emailId;

    private Long contactNumber;

    @Column(name = "is_business")
    private Boolean isBusiness;

    private String gender;

    @Column(name = "account_status")
    private String accountStatus;

    @Column(name = "pwd")
    private String password;

}
