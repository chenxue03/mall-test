package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.CmsPrefrenceArea;
import com.macro.mall.service.CmsPrefrenceAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品优选管理Controller
 * Created by macro on 2018/6/1.
 */
@Controller
@Api(tags = "CmsPrefrenceAreaController")
@Tag(name = "CmsPrefrenceAreaController", description = "商品优选管理")
@RequestMapping("/prefrenceArea")
public class CmsPrefrenceAreaController {
    @Autowired
    private CmsPrefrenceAreaService prefrenceAreaService;

    @ApiOperation("获取所有商品优选")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsPrefrenceArea>> listAll() {
        List<CmsPrefrenceArea> prefrenceAreaList = prefrenceAreaService.listAll();
        return CommonResult.success(prefrenceAreaList);
    }
    
    // 这是一个冒泡排序
	@ApiOperation("获取所有商品优选")
	@RequestMapping(value = "/sort", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<CmsPrefrenceArea>> sort() {
		List<CmsPrefrenceArea> prefrenceAreaList = prefrenceAreaService.listAll();
		for (int i = 0; i < prefrenceAreaList.size() - 1; i++) {
			for (int j = 0; j < prefrenceAreaList.size() - i - 1; j++) {
				if (prefrenceAreaList.get(j).getId() > prefrenceAreaList.get(j + 1).getId()) {
					CmsPrefrenceArea temp = prefrenceAreaList.get(j);
					prefrenceAreaList.set(j, prefrenceAreaList.get(j + 1));
					prefrenceAreaList.set(j + 1, temp);
				}
			}
		}
		return CommonResult.success(prefrenceAreaList);
	}
	
	int aa = 1;
	int bb = 2;
	int cc = 3;
}
