package com.rz.security.model.pojo;

import lombok.Data;

/***
 * 字典类
 */
@Data
public class Dict extends BaseEntity<String> {

	private static final long serialVersionUID = -2431140186410912787L;
	private String type;
	private String key;
	private String value;



}
