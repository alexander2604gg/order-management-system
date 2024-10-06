package com.alexandersaul.orders.service.impl;

import com.alexandersaul.orders.dto.order.OrderBoletaDTO;
import com.alexandersaul.orders.dto.order.OrderResponseDTO;
import com.alexandersaul.orders.dto.orderdetail.OrderDetailResponseDTO;
import com.alexandersaul.orders.service.IPdfService;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
public class PdfService implements IPdfService {

    @Autowired
    private OrderService orderService;

    public ByteArrayInputStream generatePdf(Long orderId) {
        OrderBoletaDTO orderBoletaDTO = orderService.createBoleta(orderId);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Crear un escritor PDF
            PdfWriter writer = new PdfWriter(out);
            // Crear el documento PDF
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Título de la boleta
            document.add(new Paragraph("Boleta de Venta").setBold().setFontSize(18).setMarginBottom(10));

            // Información de la orden
            OrderResponseDTO order = orderBoletaDTO.getOrderResponseDTO();
            document.add(new Paragraph("Código de la orden: " + order.getOrderId()));
            document.add(new Paragraph("Dni del cliente: " + order.getUserId()));
            document.add(new Paragraph("Fecha: " + order.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            document.add(new Paragraph("Total: S/. " + order.getTotalAmount()).setBold().setMarginTop(10));

            // Tabla de detalles de la orden
            Table table = new Table(new float[]{2, 4, 2, 2}); // 4 columnas con proporciones
            table.setWidth(UnitValue.createPercentValue(100)); // Ajustar el ancho al 100%

            // Encabezados de la tabla
            table.addHeaderCell(new Cell().add(new Paragraph("ID")));
            table.addHeaderCell(new Cell().add(new Paragraph("Producto")));
            table.addHeaderCell(new Cell().add(new Paragraph("Cantidad")));
            table.addHeaderCell(new Cell().add(new Paragraph("Precio Unitario")));

            // Agregar filas a la tabla
            for (OrderDetailResponseDTO detail : orderBoletaDTO.getOrderDetailResponseDTOList()) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(detail.getOrderDetailId()))));
                table.addCell(new Cell().add(new Paragraph(detail.getProductName())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(detail.getQuantity()))));
                table.addCell(new Cell().add(new Paragraph("S/. " + detail.getPricePerUnit())));
            }

            // Agregar tabla al documento
            document.add(table);

            // Cerrar el documento
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());

    }


}
