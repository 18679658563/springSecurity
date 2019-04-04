package com.rz.security.controller;

import com.rz.security.annotation.LogAOP;
import com.rz.security.mapper.DictMapper;
import com.rz.security.page.PageTableHandler;
import com.rz.security.page.PageTableRequest;
import com.rz.security.page.PageTableResponse;
import com.rz.security.pojo.Dict;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dicts")
public class DictController {

	@Autowired
	private DictMapper dictDao;

	@LogAOP(description = "添加字典")
	@PreAuthorize("hasAuthority('sys:dict:add')")
	@PostMapping
	public Dict save(@RequestBody Dict dict) {
		Dict d = dictDao.getByTypeAndK(dict.getType(), dict.getK());
		if (d != null) {
			throw new IllegalArgumentException("类型和key已存在");
		}
		dictDao.save(dict);
		return dict;
	}

	@GetMapping("/{id}")
	public Dict get(@PathVariable String id) {
		return dictDao.getById(id);
	}

	@LogAOP(description = "修改字典")
	@PreAuthorize("hasAuthority('sys:dict:add')")
	@PutMapping
	public Dict update(@RequestBody Dict dict) {
		dictDao.update(dict);

		return dict;
	}

	@LogAOP(description = "删除字典")
	@PreAuthorize("hasAuthority('sys:dict:del')")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		dictDao.delete(id);
	}

	@PreAuthorize("hasAuthority('sys:dict:query')")
	@GetMapping(params = { "start", "length" })
	public PageTableResponse list(PageTableRequest request) {
		return new PageTableHandler(new PageTableHandler.CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return dictDao.count(request.getParams());
			}
		}, new PageTableHandler.ListHandler() {

			@Override
			public List<Dict> list(PageTableRequest request) {
				return dictDao.list(request.getParams(), request.getOffset(), request.getLimit());
			}
		}).handle(request);
	}

	@PreAuthorize("hasAuthority('sys:dict:query')")
	@GetMapping(params = "type")
	public List<Dict> listByType(String type) {
		return dictDao.listByType(type);
	}
}
