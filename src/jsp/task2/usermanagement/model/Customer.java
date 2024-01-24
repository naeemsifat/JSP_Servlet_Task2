package jsp.task2.usermanagement.model;

public class Customer {
	private Long id;
	private Integer trNumber;
	private Integer knitCardNumber;
	private Double requisitionQuantity;
	private Double knitCardQuantity;
	private Double knitCardBalance;
	
	public Customer(Long id, Integer trNumber, Integer knitCardNumber, Double requisitionQuantity,
			Double knitCardQuantity, Double knitCardBalance) {
		super();
		this.id = id;
		this.trNumber = trNumber;
		this.knitCardNumber = knitCardNumber;
		this.requisitionQuantity = requisitionQuantity;
		this.knitCardQuantity = knitCardQuantity;
		this.knitCardBalance = knitCardBalance;
	}
	
	
	
	public Customer(Integer trNumber, Integer knitCardNumber, Double requisitionQuantity, Double knitCardQuantity,
			Double knitCardBalance) {
		super();
		this.trNumber = trNumber;
		this.knitCardNumber = knitCardNumber;
		this.requisitionQuantity = requisitionQuantity;
		this.knitCardQuantity = knitCardQuantity;
		this.knitCardBalance = knitCardBalance;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getTrNumber() {
		return trNumber;
	}
	public void setTrNumber(Integer trNumber) {
		this.trNumber = trNumber;
	}
	public Integer getKnitCardNumber() {
		return knitCardNumber;
	}
	public void setKnitCardNumber(Integer knitCardNumber) {
		this.knitCardNumber = knitCardNumber;
	}
	public Double getRequisitionQuantity() {
		return requisitionQuantity;
	}
	public void setRequisitionQuantity(Double requisitionQuantity) {
		this.requisitionQuantity = requisitionQuantity;
	}
	public Double getKnitCardQuantity() {
		return knitCardQuantity;
	}
	public void setKnitCardQuantity(Double knitCardQuantity) {
		this.knitCardQuantity = knitCardQuantity;
	}
	public Double getKnitCardBalance() {
		return knitCardBalance;
	}
	public void setKnitCardBalance(Double knitCardBalance) {
		this.knitCardBalance = knitCardBalance;
	}
	
}
