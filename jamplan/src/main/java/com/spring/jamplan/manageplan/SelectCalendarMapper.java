package com.spring.jamplan.manageplan;

import java.util.ArrayList;

import com.spring.jamplan.model.CalendarVO;

public interface SelectCalendarMapper {
	public ArrayList<CalendarVO> getCalendarSelectDate(int planNo);
}
