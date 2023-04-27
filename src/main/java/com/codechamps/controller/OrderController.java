package com.codechamps.controller;

import com.codechamps.data.Order;
import com.codechamps.helper.Helper;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

	@Autowired
	ServletContext servletContext;

	private final TemplateEngine templateEngine;
	
    @RequestMapping(path = "/")
    public String getOrderPage(Model model) {
        Order order = Helper.getOrder();
        model.addAttribute("orderEntry", order);
        return "order";
    }
    
    @RequestMapping(path = "/pdf")
    public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	 Order order = Helper.getOrder();
    	 
    	 WebContext context = new WebContext(request, response, servletContext);
         context.setVariable("orderEntry", order);
         String orderHtml = templateEngine.process("order", context); // order is the name of template stored in the resources folder
         
         ByteArrayOutputStream target = new ByteArrayOutputStream();
         ConverterProperties converterProperties = new ConverterProperties();
         converterProperties.setBaseUri("http://localhost:8080");
         HtmlConverter.convertToPdf(orderHtml, target, converterProperties);
         
         byte[] bytes = target.toByteArray();
         
         FileOutputStream out = new FileOutputStream("out.pdf");
         out.write(bytes);
         out.close();

         return ResponseEntity.ok()
                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
                 .contentType(MediaType.APPLICATION_PDF)
                 .body(bytes);
    }    
}
