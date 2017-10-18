package org.cityu.cs.ian.util.unuse;

import org.cityu.cs.ian.util.unuse.Component;

import java.util.ArrayList;
import java.util.List;

public class StaticComponent {

	public static List<Component> list;
	public static List<Component> ref(){
		list=new ArrayList<Component>();
		list.add(new Component(1, "001", "系统管理", "M", "#", "system", "T"));
		return list;
	}
}
