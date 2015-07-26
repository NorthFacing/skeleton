package com.mall.biz.demo.model;

import java.time.LocalDateTime;

import com.mall.core.base.model.BaseModel;

public class Demo extends BaseModel {

    private String name;
    private Integer age;
    private LocalDateTime birthDay;
    private Double score;
    private Boolean isGraduated;

    public String getName() {
        return name;
    }

    public Demo setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Demo setAge(Integer age) {
        this.age = age;
        return this;
    }

    public LocalDateTime getBirthDay() {
        return birthDay;
    }

    public Demo setBirthDay(LocalDateTime birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public Double getScore() {
        return score;
    }

    public Demo setScore(Double score) {
        this.score = score;
        return this;
    }

    public Boolean getIsGraduated() {
        return isGraduated;
    }

    public Demo setIsGraduated(Boolean isGraduated) {
        this.isGraduated = isGraduated;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(Demo.class.getName());
        sb.append(" [ ");
        sb.append("id=").append(this.id).append(", ");
        sb.append("name=").append(this.name).append(", ");
        sb.append("age=").append(this.age).append(", ");
        sb.append("birthDay=").append(this.birthDay).append(", ");
        sb.append("score=").append(this.score).append(", ");
        sb.append("isGraduated=").append(this.isGraduated).append(", ");
        sb.append("createUser=").append(this.createUser).append(", ");
        sb.append("createTime=").append(this.createTime).append(", ");
        sb.append("updateUser=").append(this.updateUser).append(", ");
        sb.append("updateTime=").append(this.updateTime);
        sb.append(" ]");
        return sb.toString();
    }
}
