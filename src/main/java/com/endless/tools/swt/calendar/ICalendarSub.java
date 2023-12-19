package com.endless.tools.swt.calendar;
import org.eclipse.swt.graphics.Point;
/**
 * @author cr.wu
 *
 * 2015??7??è21??
 */
public interface ICalendarSub {
	/**设置日历的坐标*/
	Point getDtLocation();
	/**返回时间**/
	void setDate(String date);
}

