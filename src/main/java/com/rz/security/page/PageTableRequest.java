package com.rz.security.page;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 分页查询参数
 * User: silence
 * Date: 2018-01-29
 * Time: 下午4:37
 */
@Data
public class PageTableRequest implements Serializable {

	private static final long serialVersionUID = 7328071045193618467L;

	private Integer offset;
	private Integer limit;
	private Map<String, Object> params;
}
