package com.macro.mall.service.impl;

import com.macro.mall.dao.OmsOrderDao;
import com.macro.mall.dao.OmsOrderOperateHistoryDao;
import com.macro.mall.dto.OmsOrderDeliveryParam;
import com.macro.mall.model.OmsOrderOperateHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.github.pagehelper.PageHelper;
import com.macro.mall.dto.OmsOrderQueryParam;
import com.macro.mall.model.OmsOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OmsOrderServiceImplTest{

    private OmsOrderQueryParam queryParam;

    private List<OmsOrder> mockOrderList;

    @Mock
    private OmsOrderDao orderDao;

    @Mock
    private OmsOrderOperateHistoryDao orderOperateHistoryDao;

    @InjectMocks
    private OmsOrderServiceImpl omsOrderService;

    private List<OmsOrderDeliveryParam> deliveryParamList;

    @BeforeEach
    public void setUp() {
        OmsOrderDeliveryParam param1 = new OmsOrderDeliveryParam();
        param1.setOrderId(1L);
    
        OmsOrderDeliveryParam param2 = new OmsOrderDeliveryParam();
        param2.setOrderId(2L);
    
        deliveryParamList = Arrays.asList(param1, param2);
    }

    @Test
    public void testDelivery() {
        // Mock the behavior of orderDao.delivery
        when(orderDao.delivery(deliveryParamList)).thenReturn(2);
    
        // Call the method under test
        int result = omsOrderService.delivery(deliveryParamList);
    
        // Verify the interactions and assert the results
        verify(orderDao, times(1)).delivery(deliveryParamList);
    
        List<OmsOrderOperateHistory> expectedHistoryList = Arrays.asList(
                createOperateHistory(1L),
                createOperateHistory(2L)
        );
    
        verify(orderOperateHistoryDao, times(1)).insertList(anyList());
    
        assertEquals(2, result);
    }

    private OmsOrderOperateHistory createOperateHistory(Long orderId) {
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(orderId);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(2);
        history.setNote("完成发货");
        return history;
    }

    @BeforeEach
    public void setUp1() {
        queryParam = new OmsOrderQueryParam();
        // 初始化查询参数，根据需要设置查询条件
        queryParam.setStatus(1); // 示例条件，根据实际情况设置
    
        mockOrderList = Arrays.asList(
                new OmsOrder(),
                new OmsOrder()
        );
    }

    @Test
    public void testList() {
        // 设置分页参数
        int pageSize = 10;
        int pageNum = 1;
    
        // 模拟orderDao.getList方法的行为
        when(orderDao.getList(queryParam)).thenReturn(mockOrderList);
    
        // 调用被测方法
        List<OmsOrder> result = omsOrderService.list(queryParam, pageSize, pageNum);
    
        // 验证PageHelper.startPage方法被调用
        verifyStatic(PageHelper.class);
        PageHelper.startPage(pageNum, pageSize);
    
        // 验证orderDao.getList方法被调用，并且参数正确
        verify(orderDao, times(1)).getList(queryParam);
    
        // 验证返回结果
        assertEquals(mockOrderList, result);
    }

}

