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
@Table(name = "business")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "address")
    private String businessAddress;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "contact_number")
    private Long businesscontactNumber;

}
