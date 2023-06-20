package cop.project.order.dbo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "orders")
public class Order {

    @Id
    private Integer id;
    private Integer buyerId;
    private List<String> products;

}
