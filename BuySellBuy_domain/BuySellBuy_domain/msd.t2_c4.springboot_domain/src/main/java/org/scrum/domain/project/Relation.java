import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Relation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer relationId;

	private String relationName;
	@Enumerated(EnumType.STRING)
	private RelationType relationType;
	private String address;

	@ManyToOne
	@JoinColumn(name = "CurrencyId")
	private Currency currency;


	@ManyToOne
	@JoinColumn(name = "PaymentId")
	private PaymentCondition paymentCondition;

	// get & set (pentru atribute)
	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public RelationType getRelationType() {
		return relationType;
	}

	public void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Relation(String relationName, RelationType relationType, String address, Currency currency, PaymentCondition paymentCondition) {
		this.relationName = relationName;
		this.relationType = relationType;
		this.address = address;
		this.currency = currency;
		this.paymentCondition = paymentCondition;
	}

}
