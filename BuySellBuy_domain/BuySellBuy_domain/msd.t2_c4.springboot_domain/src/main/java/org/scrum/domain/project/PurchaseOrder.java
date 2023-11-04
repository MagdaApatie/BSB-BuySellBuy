import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PurchaseOrder implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer purchaseOrderId;

	@ManyToOne
	@JoinColumn(name = "RelationId")
	private Relation relation;

	private Date orderDate;

	@ManyToOne
	@JoinColumn(name = "CurrencyId")
	private Currency currency;

	private double purchaseOrderValue;

	@OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PurchaseOrderLine> purchaseOrderLines;

	public PurchaseOrder (Relation relation, Date orderDate, Currency currency,double purchaseOrderValue ) {
		this.relation = relation;
		this.orderDate = orderDate;
		this.currency = currency;
		this.purchaseOrderValue = calculatePurchaseOrderValue();
	}


	public void calculatePurchaseOrderValue() {
		double totalValue = 0;
		for (PurchaseOrderLine line : purchaseOrderLines) {
			totalValue += line.getPurchaseOrderLineValue();
		}
		this.purchaseOrderValue = totalValue;
	}
}
