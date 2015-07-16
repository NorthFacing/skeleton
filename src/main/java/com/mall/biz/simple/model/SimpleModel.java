package com.mall.biz.simple.model;

import java.util.Date;

/**
 * demo
 * @since v0.0.1
 * @author Bob
 */
public class SimpleModel {

	private String id;    //  
	private String name;    //  
	private Integer age;    //  
	private Date birthDay;    //  
	private double score;    //  
	private boolean isGraduated;    //  
	private Date createTime;    //  
	private String createUser;    //  
	private Date updateTime;    //  
	private String updateUser;    //  
		
	/**  */
	public String getId() {
		return id;
	}
	public SimpleModel setId (String id) {
		this.id = id;
		return this;
	}
	/**  */
	public String getName() {
		return name;
	}
	public SimpleModel setName (String name) {
		this.name = name;
		return this;
	}
	/**  */
	public Integer getAge() {
		return age;
	}
	public SimpleModel setAge (Integer age) {
		this.age = age;
		return this;
	}
	/**  */
	public Date getBirthDay() {
		return birthDay;
	}
	public SimpleModel setBirthDay (Date birthDay) {
		this.birthDay = birthDay;
		return this;
	}
	/**  */
	public double getScore() {
		return score;
	}
	public SimpleModel setScore (double score) {
		this.score = score;
		return this;
	}
	/**  */
	public boolean getIsGraduated() {
		return isGraduated;
	}
	public SimpleModel setIsGraduated (boolean isGraduated) {
		this.isGraduated = isGraduated;
		return this;
	}
	/**  */
	public Date getCreateTime() {
		return createTime;
	}
	public SimpleModel setCreateTime (Date createTime) {
		this.createTime = createTime;
		return this;
	}
	/**  */
	public String getCreateUser() {
		return createUser;
	}
	public SimpleModel setCreateUser (String createUser) {
		this.createUser = createUser;
		return this;
	}
	/**  */
	public Date getUpdateTime() {
		return updateTime;
	}
	public SimpleModel setUpdateTime (Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	/**  */
	public String getUpdateUser() {
		return updateUser;
	}
	public SimpleModel setUpdateUser (String updateUser) {
		this.updateUser = updateUser;
		return this;
	}
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Demo");
        sb.append(" [ ");
        sb.append("id=").append(this.id).append(", ");
        sb.append("name=").append(this.name).append(", ");
        sb.append("age=").append(this.age).append(", ");
        sb.append("birthDay=").append(this.birthDay).append(", ");
        sb.append("score=").append(this.score).append(", ");
        sb.append("isGraduated=").append(this.isGraduated).append(", ");
        sb.append("createTime=").append(this.createTime).append(", ");
        sb.append("createUser=").append(this.createUser).append(", ");
        sb.append("updateTime=").append(this.updateTime).append(", ");
        sb.append("updateUser=").append(this.updateUser).append(", ");
        sb.append(" ]");
        return sb.toString();
    }
}
