package com.example.dneshiboshiken;
 
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
 
@Root
public class Plan {
 @Element(name="planName")
    public String plan;
 @Element
    public String date;
 @Element
    public String startTime;
 @Element
 	 public String endTime;
 @Element
 	public String alerm;
 
}

