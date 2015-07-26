package com.bob.biz.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.AjaxResults;
import com.bob.biz.hotel.model.Hotel;
import com.bob.biz.hotel.model.HotelVo;
import com.bob.biz.hotel.service.HotelService;

/**
 * hotelController
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-25 15:14:35
 */
@Controller
public class HotelController extends BaseController {

    @Autowired
    private HotelService hotelService;

    @ResponseBody
    @RequestMapping(value = "/hotel/edit", method = RequestMethod.POST)
    public AjaxResults<?> add(@Validated Hotel hotel) {
        hotelService.edit(hotel);
        return AjaxResults.success();
    }

    @ResponseBody
    @RequestMapping(value = "/hotel/getById", method = RequestMethod.GET)
    public AjaxResults<?> getById(String id) {
        return new AjaxResults<Hotel>(hotelService.getById(id));
    }

    @RequestMapping(value = "/hotel/listHtml", method = RequestMethod.GET)
    public String listHtml(Model model) {
        return "/hotel/list";
    }

    @ResponseBody
    @RequestMapping(value = "/hotel/getList", method = RequestMethod.POST)
    public AjaxResults<List<?>> getList(HotelVo hotelVo) {
        return new AjaxResults<List<?>>(hotelService.getList(hotelVo));
    }

    @ResponseBody
    @RequestMapping(value = "/hotel/getPage", method = RequestMethod.POST)
    public AjaxResults<PageInfo<?>> getPage(HotelVo hotelVo) {
        PageInfo<Hotel> pageInfo = hotelService.getPage(hotelVo.getPage(), hotelVo.getRows(), hotelVo);
        return new AjaxResults<PageInfo<?>>(pageInfo);
    }

}
