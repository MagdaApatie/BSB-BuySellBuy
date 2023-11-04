import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PurchaseOrderLine implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer purchaseOrderLineId;

	@ManyToOne
	@JoinColumn(name = "PurchaseOrderId")
	private PurchaseOrder purchaseOrder;

	@ManyToOne
	@JoinColumn(name = "ProductId")
	private Product product;

	private int quantity;
	private double price;
	private double purchaseOrderLineValue;

	// get & set (pentru atribute)
	public Integer getPurchaseOrderLineId() {
		return purchaseOrderLineId;
	}

	public void setPurchaseOrderLineId(Integer purchaseOrderLineId) {
		this.purchaseOrderLineId = purchaseOrderLineId;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
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

	public double getPurchaseOrderLineValue() {
		return purchaseOrderLineValue;
	}

	//set pentru PurchaseOrderLineValue nu avem nevoie, se calculeaza automat

	// Constructori

	public PurchaseOrderLine(PurchaseOrder purchaseOrder, Product product, int quantity, double price, double purchaseOrderLineValue) {
		this.purchaseOrder = purchaseOrder;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.purchaseOrderLineValue = calculatePurchaseOrderLineValue();
	}

	public void calculatePurchaseOrderLineValue() {
		this.purchaseOrderLineValue = quantity * price;
	}
}
