package com.rz.security.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description: token
 * User: silence
 * Date: 2019-01-29
 * Time: 下午3:02
 */
@Data
public class TokenDto implements Serializable {

	private static final long serialVersionUID = 6314027741784310221L;

	private String token;
	/** 登陆时间戳（毫秒） */
	private Long loginTime;

	public TokenDto(String token, Long loginTime) {
		super();
		this.token = token;
		this.loginTime = loginTime;
	}


}
