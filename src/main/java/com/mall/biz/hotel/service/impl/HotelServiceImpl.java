package com.mall.biz.hotel.service.impl;

import org.springframework.stereotype.Service;

import com.mall.biz.hotel.model.Hotel;
import com.mall.biz.hotel.model.HotelVo;
import com.mall.biz.hotel.service.HotelService;
import com.mall.core.base.service.impl.BaseServiceImpl;

/**
 * hotelServiceImpl
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-25 15:14:35
 */
@Service("hotelService")
public  class HotelServiceImpl extends BaseServiceImpl<Hotel, HotelVo> implements HotelService {

}