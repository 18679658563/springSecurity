package com.rz.security.controller;

import com.rz.security.annotation.LogAOP;
import com.rz.security.mapper.DictMapper;
import com.rz.security.page.PageTableHandler;
import com.rz.security.page.PageTableRequest;
import com.rz.security.page.PageTableResponse;
import com.rz.security.model.pojo.Dict;
import com.rz.security.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dicts")
public class DictController {

	@Autowired
	private DictMapper dictMapper;

	@LogAOP(description = "添加字典")
	@PreAuthorize("hasAuthority('sys:dict:add')")
	@PostMapping
	public Dict save(@RequestBody Dict dict) {
		List<Dict> d = dictMapper.selectByDict(dict);
		if (!CollectionUtils.isEmpty(d)) {
			throw new IllegalArgumentException("类型和key已存在");
		}
		dict.setId(UUIDUtil.getUUID());
		dictMapper.insertDict(dict);
		return dict;
	}

	@GetMapping("/{id}")
	public Dict get(@PathVariable String id) {
		Dict dict = dictMapper.selectById(id);
		return dict;
	}

	@LogAOP(description = "修改字典")
	@PreAuthorize("hasAuthority('sys:dict:add')")
	@PutMapping
	public Dict update(@RequestBody Dict dict) {
		dictMapper.update(dict);

		return dict;
	}

	@LogAOP(description = "删除字典")
	@PreAuthorize("hasAuthority('sys:dict:del')")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		dictMapper.delete(id);
	}

	@PreAuthorize("hasAuthority('sys:dict:query')")
	@GetMapping(params = {"start", "length"})
	public PageTableResponse list(PageTableRequest request) {
		return new PageTableHandler(new PageTableHandler.CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return dictMapper.count(request.getParams());
			}
		}, new PageTableHandler.ListHandler() {

			@Override
			public List<Dict> list(PageTableRequest request) {
				return dictMapper.list(request.getParams(), request.getOffset(), request.getLimit());
			}
		}).handle(request);
	}

	@PreAuthorize("hasAuthority('sys:dict:query')")
	@GetMapping(params = "type")
	public List<Dict> listByType(String type) {
		Dict dict = new Dict();
		dict.setType(type);
		return dictMapper.selectByDict(dict);
	}

}
