import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer productId;

	private String productName;
	private double purchasePrice;
	private double salesPrice;
	private ProductState productState;

	// get & set (pentru atribute)
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public ProductState getProductState() {
		return productState;
	}

	public void setproductState(ProductState productState) {
		this.productState = productState;
	}

// constructor

	public Product(String productName, double purchasePrice, double salesPrice, ProductState productState) {
		this.productName = productName;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
		this.productState = productState;
	}

}
