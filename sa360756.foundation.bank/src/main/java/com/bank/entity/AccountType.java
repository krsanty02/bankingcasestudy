package com.bank.entity;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class AccountType {
	private String current;
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public Saving getSaving() {
		return saving;
	}
	public void setSaving(Saving saving) {
		this.saving = saving;
	}
	@Embedded
	private Saving saving;

}
