package com.mzt.logserver.function;

import com.mzt.logapi.service.IParseFunction;
import com.mzt.logserver.beans.Order;
import com.mzt.logserver.service.OrderQueryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author muzhantong
 * create on 2021/2/9 5:20 下午
 */
@Component
public class OrderParseFunction implements IParseFunction {
    @Resource
    @Lazy
    private OrderQueryService orderQueryService;

    @Override
    public String functionName() {
        return "ORDER";
    }

    @Override
    public String apply(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        Order order = orderQueryService.queryOrder(Long.parseLong(value));
        return order.getProductName().concat("(").concat(value).concat(")");
    }
}
