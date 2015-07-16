package com.mall.biz.simple.model;

import java.util.Date;

import javax.persistence.Id;

/**
 * demo
 * @since v0.0.1
 * @author Bob
 */
public class Simple {

    @Id
	private Integer id;    //  
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
	public Integer getId() {
		return id;
	}
	public Simple setId (Integer id) {
		this.id = id;
		return this;
	}
	/**  */
	public String getName() {
		return name;
	}
	public Simple setName (String name) {
		this.name = name;
		return this;
	}
	/**  */
	public Integer getAge() {
		return age;
	}
	public Simple setAge (Integer age) {
		this.age = age;
		return this;
	}
	/**  */
	public Date getBirthDay() {
		return birthDay;
	}
	public Simple setBirthDay (Date birthDay) {
		this.birthDay = birthDay;
		return this;
	}
	/**  */
	public double getScore() {
		return score;
	}
	public Simple setScore (double score) {
		this.score = score;
		return this;
	}
	/**  */
	public boolean getIsGraduated() {
		return isGraduated;
	}
	public Simple setIsGraduated (boolean isGraduated) {
		this.isGraduated = isGraduated;
		return this;
	}
	/**  */
	public Date getCreateTime() {
		return createTime;
	}
	public Simple setCreateTime (Date createTime) {
		this.createTime = createTime;
		return this;
	}
	/**  */
	public String getCreateUser() {
		return createUser;
	}
	public Simple setCreateUser (String createUser) {
		this.createUser = createUser;
		return this;
	}
	/**  */
	public Date getUpdateTime() {
		return updateTime;
	}
	public Simple setUpdateTime (Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	/**  */
	public String getUpdateUser() {
		return updateUser;
	}
	public Simple setUpdateUser (String updateUser) {
		this.updateUser = updateUser;
		return this;
	}
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(Simple.class.getName());
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
