package com.example.dneshiboshiken;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

	@Root
	public class PlanShelf{
	    @ElementList(name="planlist")
	    public List<Plan> plan;

	}
