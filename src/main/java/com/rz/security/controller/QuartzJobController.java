package com.rz.security.controller;

import com.rz.security.annotation.LogAOP;
import com.rz.security.mapper.QuartzJobMapper;
import com.rz.security.page.PageTableHandler;
import com.rz.security.page.PageTableRequest;
import com.rz.security.page.PageTableResponse;
import com.rz.security.pojo.QuartzJob;
import com.rz.security.service.IQuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-04-11
 * Time: 下午3:57
 */
@RestController
@RequestMapping("/quartzs")
public class QuartzJobController {

    @Autowired
    private IQuartzJobService quartzJobService;

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @PostMapping
    @LogAOP(description = "添加定时任务")
    @PreAuthorize("hasAuthority('sys:quartz:add')")
    public void saveQuartzJob(@RequestBody QuartzJob quartzJob){
        quartzJobService.addQuartzJob(quartzJob);
    }

    @PutMapping
    @LogAOP(description = "修改定时任务信息")
    @PreAuthorize("hasAnyAuthority('sys:quartz:update')")
    public void modifyQuartzJob(@RequestBody QuartzJob quartzJob){
        quartzJobService.updateQuartzJob(quartzJob);
    }

    @PutMapping("/{id}")
    @LogAOP(description = "开启或关闭定时任务")
    @PreAuthorize("hasAnyAuthority('sys:quartz:update')")
    public void onOrOffQuartzJob(@PathVariable String id){
        QuartzJob job = quartzJobMapper.selectById(id);
        quartzJobService.updateIsPause(job);
    }

    @DeleteMapping("/{id}")
    @LogAOP(description = "删除定时任务")
    @PreAuthorize("hasAnyAuthority('sys:quartz:update')")
    public void delQuartz(@PathVariable  String id){
        quartzJobService.removeQuartz(id);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('sys:quartz:query')")
    public PageTableResponse listQuartz(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return quartzJobMapper.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<QuartzJob> list(PageTableRequest request) {
                return quartzJobMapper.selectByPage(request.getParams(),request.getOffset(),request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:quartz:query')")
    public QuartzJob getQuartz(@PathVariable String id) {
      return  quartzJobMapper.selectById(id);
    }
}
