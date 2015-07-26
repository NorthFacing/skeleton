package com.bob.biz.simple.model;

import java.time.LocalDateTime;

/**
 * demo
 * 
 * @since v0.0.1
 * @author Bob
 */

public class SimpleVo extends Simple {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}