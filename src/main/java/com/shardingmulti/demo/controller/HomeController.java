package com.shardingmulti.demo.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shardingmulti.demo.mapper.goodsdb.GoodsMapper;
import com.shardingmulti.demo.mapper.sharding.OrderShardingMapper;
import com.shardingmulti.demo.pojo.Goods;

import javax.annotation.Resource;

import com.shardingmulti.demo.pojo.OrderSharding;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private OrderShardingMapper orderShardingMapper;

    //商品详情 参数:商品id
    @GetMapping("/goodsinfo")
    @ResponseBody
    @DS("goodsdb")
    public Goods goodsInfo(@RequestParam(value="goodsid",required = true,defaultValue = "0") Long goodsId) {
        Goods goods = goodsMapper.selectOneGoods(goodsId);
        return goods;
    }

    @GetMapping("/orderlist")
    public String list(Model model, @RequestParam(value="currentPage",required = false,defaultValue = "1") Integer currentPage){

       PageHelper.startPage(currentPage, 5);

        List<OrderSharding> orderList = orderShardingMapper.selectAllOrder();
        /*
        for (int i = 0; i < orderList.size(); i++) {
            OrderSharding s = (OrderSharding)orderList.get(i);
            System.out.println("----------:"+s.getOrderId()+"  "+s.getGoodsName());
        }
        */
        model.addAttribute("orderlist",orderList);
        PageInfo<OrderSharding> pageInfo = new PageInfo<>(orderList);
        model.addAttribute("pageInfo", pageInfo);
        System.out.println("------------------------size:"+orderList.size());
        return "order/list";
    }
}

