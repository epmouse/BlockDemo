package org.cityu.cs.ian.util.unuse;

import java.io.Serializable;

/**
 * 
  * 类名:Component.java
  * 作者:王凡
  * 日期:2016年11月23日 上午9:55:13 
  * 说明:功能模块常量,type:M-模块；B-按钮；
  * PID为层级关系：如：根级001，子集1 001001，子集2 001002 子集2的子集001002001
 */
public class Component implements Serializable{
	
	
	/** *
	    * serialVersionUID:TODO（用一句话描述这个变量表示什么） * * 
	    * @since Ver 1.1 
	    */ 
	private static final long serialVersionUID = 1L;
	public Component(int id, String pid, String compName, String compType, String urlStr, String authorStr,
			String status) {
		super();
		this.id = id;
		this.pid = pid;
		this.compName = compName;
		this.compType = compType;
		this.urlStr = urlStr;
		this.authorStr = authorStr;
		this.status = status;
	}

	/**主键，数字ID**/
	int id;
	/**父级ID，数字ID**/
	String pid;
	/**模块名**/
	String compName;
	/**模块类型**/
	String compType;
	/**触发链接**/
	String urlStr;
	/**shiro标签**/
	String authorStr;
	/**状态**/
	String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getUrlStr() {
		return urlStr;
	}
	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
	public String getAuthorStr() {
		return authorStr;
	}
	public void setAuthorStr(String authorStr) {
		this.authorStr = authorStr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCompType() {
		return compType;
	}
	public void setCompType(String compType) {
		this.compType = compType;
	}
	
}
