package com.eeit162.FWBweb.hc.advertising.model.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "plans")
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plan_id")
	private Integer planId;

	@Column(name = "period", nullable = false, length = 100)
	private String period;

	@Column(name = "price", nullable = false)
	private Integer price;

	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
	private List<Advertisement> advertisements;

	public Plan() {
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

//	public List<Advertisement> getAdvertisements() {
//		return advertisements;
//	}

	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", period=" + period + ", price=" + price + ", advertisements="
				+ advertisements + "]";
	}

}
