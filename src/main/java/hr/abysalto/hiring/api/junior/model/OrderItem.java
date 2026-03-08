package hr.abysalto.hiring.api.junior.model;

import java.math.BigDecimal;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;;

@Data
@Table("ORDER_ITEM")
public class OrderItem {
	@Id
	private Long orderItemId;
	private Long orderNr;
	private Short itemNt;
	private String name;
	private Short quantity;
	private BigDecimal price;
}
