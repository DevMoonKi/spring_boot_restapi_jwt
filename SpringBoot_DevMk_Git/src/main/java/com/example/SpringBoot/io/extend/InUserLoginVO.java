package com.example.SpringBoot.io.extend;

import java.util.concurrent.TimeUnit;

import com.example.SpringBoot.io.MemberVO;

import io.swagger.annotations.ApiModelProperty;

public class InUserLoginVO extends MemberVO {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="token 시간")
	private int timeout;
	@ApiModelProperty(value="token 시간단위")
	private TimeUnit timeUnit;

	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
	@Override
	public String toString() {
		return "InUserLoginVO [timeout=" + timeout + ", timeUnit=" + timeUnit + "]";
	}
	
}
