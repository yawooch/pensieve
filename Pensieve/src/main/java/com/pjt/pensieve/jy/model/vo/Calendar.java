package com.pjt.pensieve.jy.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calendar {
	private int calId;
	private String title;
	private Date start_date;
	private Date end_date;
}
