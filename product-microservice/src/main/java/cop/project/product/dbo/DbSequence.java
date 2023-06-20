package cop.project.product.dbo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "db_sequence")
public class DbSequence {

    @Id
    private String id;

    private Integer seqNo;

}
