import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class SalesOrderLine implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer salesOrderLineId;

	@ManyToOne
	@JoinColumn(name = "SalesOrderId")
	private SalesOrder salesOrder;

	@ManyToOne
	@JoinColumn(name = "ProductId")
	private Product product;

	private int quantity;
	private double price;
	private double salesOrderLineValue;

	//get & set (pentru atribute)
	public Integer getSalesOrderLineId() {
		return salesOrderLineId;
	}

	public void setSalesOrderLineId(Integer salesOrderLineId) {
		this.salesOrderLineId = salesOrderLineId;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSalesOrderLineValue() {
		return salesOrderLineValue;
	}

	//nu avem nevoie de set pentru valoarea liniei, deoarece este calculata autoamat

	// Other attributes and constructors

	public SalesOrderLine(SalesOrder salesOrder, Product product, int quantity, double price, double salesOrderLineValue) {
		this.salesOrder = salesOrder;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.salesOrderLineValue=calculateSalesOrderLineValue();
	}

	public void calculateSalesOrderLineValue() {
		this.salesOrderLineValue = quantity * price;
	}
}

