package com.eeit162.FWBweb.cheng.activity;


//@Controller
public class activitySignUp {
	
//	@Autowired
	private Integer activityId;

	private String activityName;
		
	private Integer activityOrder;
	
	private Integer activityPrice;
	
	
	
	public activitySignUp() {
	}

	
	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getActivityOrder() {
		return activityOrder;
	}

	public void setActivityOrder(Integer activityOrder) {
		this.activityOrder = activityOrder;
	}

	public Integer getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(Integer activityPrice) {
		this.activityPrice = activityPrice;
	}

	
}
