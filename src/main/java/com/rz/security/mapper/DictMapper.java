package com.rz.security.mapper;

import com.rz.security.pojo.Dict;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface DictMapper {

	Dict selectById(String id);

	int delete(String id);

	int update(Dict dict);

	int insertDict(Dict dict);

	int count(@Param("params") Map<String, Object> params);

	List<Dict> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                    @Param("limit") Integer limit);

	List<Dict> selectByDict(Dict dict);
}
