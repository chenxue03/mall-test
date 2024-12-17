package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.CmsSubject;
import com.macro.mall.service.CmsSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品专题管理Controller
 * Created by macro on 2018/6/1.
 */
@Controller
@Api(tags = "CmsSubjectController")
@Tag(name = "CmsSubjectController", description = "商品专题管理")
@RequestMapping("/subject")
public class CmsSubjectController {
    @Autowired
    private CmsSubjectService subjectService;

    @ApiOperation("获取全部商品专题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsSubject>> listAll() {
        List<CmsSubject> subjectList = subjectService.listAll();
        return CommonResult.success(subjectList);
    }

    @ApiOperation(value = "根据专题名称分页获取商品专题")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
	public CommonResult<CommonPage<CmsSubject>> getList(
			@RequestParam(value = "keyword", required = false) String keyword, // 获取请求参数中的keyword，非必填
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, // 获取请求参数中的pageNum，默认值为1
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) { // 获取请求参数中的pageSize，默认值为5

		// 调用subjectService的list方法，根据keyword、pageNum和pageSize获取CmsSubject列表
		List<CmsSubject> subjectList = subjectService.list(keyword, pageNum, pageSize);

		// 将获取到的CmsSubject列表封装成CommonPage对象，并通过CommonResult.success方法返回
		return CommonResult.success(CommonPage.restPage(subjectList));
	}
    
    // 这是一个冒泡排序
	public static void sort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
}
