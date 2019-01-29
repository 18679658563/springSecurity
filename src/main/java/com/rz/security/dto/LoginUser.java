package com.rz.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rz.security.pojo.Permission;
import com.rz.security.pojo.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description: user登录类
 * User: silence
 * Date: 2019-01-29
 * Time: 下午3:04
 */
@Data
public class LoginUser extends User implements UserDetails {

	private static final long serialVersionUID = -1379274258881257107L;

	private List<Permission> permissions;
	private String token;
	/** 登陆时间戳（毫秒） */
	private Long loginTime;
	/** 过期时间戳 */
	private Long expireTime;

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
				.map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		// do nothing
	}

	/**
	 * 账户是否未过期
	 * @return
 	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 账户是否未锁定
	 * @return
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return getStatus() != Status.LOCKED;
	}

	/**
	 * 密码是否未过期
	 * @return
	 */
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 账户是否激活
	 * @return
 	 */
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}



}
