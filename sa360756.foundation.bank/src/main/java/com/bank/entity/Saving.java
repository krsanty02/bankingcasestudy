package com.bank.entity;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class Saving {
	private String individual;
	private String joint;
	public String getIndividual() {
		return individual;
	}
	public void setIndividual(String individual) {
		this.individual = individual;
	}
	public String getJoint() {
		return joint;
	}
	public void setJoint(String joint) {
		this.joint = joint;
	}

}
