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
public class SalesOrder implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer salesOrderId;

	@ManyToOne
	@JoinColumn(name = "RelationId")
	private Relation relation;

	@ManyToOne
	@JoinColumn(name = "PaymentId")
	private PaymentCondition paymentCondition;

	@ManyToOne
	@JoinColumn(name = "CurrencyId")
	private Currency currency;

	private double salesOrderValue;

	@OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SalesOrderLine> salesOrderLines;


 //get & set (pentru atribute)

	public Integer getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(Integer salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public PaymentCondition getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(PaymentCondition paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public double getSalesOrderValue() {
		return salesOrderValue;
	}

	//public List<SalesOrderLine> getSalesOrderLines() {
		//return salesOrderLines;
	//}

	//public void setSalesOrderLines(List<SalesOrderLine> salesOrderLines) {
		//this.salesOrderLines = salesOrderLines;
	//}

	//Constructori si metode

	public SalesOrder(Relation relation, PaymentCondition paymentCondition, Currency currency, double salesOrderValue) {
		this.relation = relation;
		this.paymentCondition = paymentCondition;
		this.currency = currency;
		this.salesOrderValue = calculateSalesOrderValue();

	public void calculateSalesOrderValue() {
		double totalValue = 0;
		for (SalesOrderLine line : salesOrderLines) {
			totalValue += line.getSalesOrderLineValue();
		}
		this.salesOrderValue = totalValue;
	}
}
