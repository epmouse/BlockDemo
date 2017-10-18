package org.cityu.cs.ian.util.unuse;

import java.util.Random;


public class NumUtil {
	public static String rand(int num){
		StringBuffer sb = new StringBuffer();
		if(num<=0){
			return "";
		}
		for(int i=0;i<num;i++){
			sb.append(new Random().nextInt(10));
		}
		return sb.toString();
		
	}
}
