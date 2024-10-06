package com.alexandersaul.orders.service;

import com.alexandersaul.orders.dto.order.OrderBoletaDTO;

import java.io.ByteArrayInputStream;

public interface IPdfService {

    ByteArrayInputStream generatePdf(Long orderId);

}
