package org.nico.ct.section;

import org.nico.aoc.aspect.point.ProcessingAspectPoint;
import org.nico.aoc.scan.annotations.Label;
import org.nico.aoc.scan.annotations.aspect.Around;
import org.nico.db.datasource.TransationManager;

//@Aspect	//这里不使用注解，使用XML配置增加清晰明了
public class TransationSection {

	@Label
	private TransationManager tm;

	@Around(value = "expression(org.nico.ct.service.impl.*.*(..))")
	public Object around(ProcessingAspectPoint point) throws Throwable {
		Object result = null;
		try {
			tm.beginTransaction();  
			result = point.process();
			tm.commitTransaction();
			tm.relaseConnection();
		}catch(Exception e) {
			tm.rollbackTransaction();
		}
		return result;
	}



}
