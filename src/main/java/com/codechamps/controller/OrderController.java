package com.codechamps.controller;

import com.codechamps.data.Billing;
import com.codechamps.data.Order;
import com.codechamps.data.TemplatesDto;
import com.codechamps.helper.Helper;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

	@Autowired
	private final ServletContext servletContext;

	private static final String TEMPLATES_DIR = "src/main/resources/templates/";

	private final TemplateEngine templateEngine;




	@PostMapping("/templates")
	public ResponseEntity<String> createTemplate(@RequestBody TemplatesDto templatesDto) throws IOException {

		String fileName = System.currentTimeMillis() + ".html";

		File file = new File(TEMPLATES_DIR + fileName);

		try (FileWriter writer = new FileWriter(file)) {
			writer.write("<html>\n<head>\n<style>\n" + templatesDto.getCss() + "\n</style>\n</head>\n<body>\n" + templatesDto.getHtml() + "\n</body>\n</html>");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body("Template created successfully!");
	}

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

	@RequestMapping(path = "/billing")
	public ResponseEntity<?> getBill(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			Billing billing = Helper.getBill();

			WebContext context = new WebContext(request, response, servletContext);
			context.setVariable("billingData", billing);
			String orderHtml = templateEngine.process("Fluwo13", context); // order is the name of template stored in the resources folder

			ByteArrayOutputStream target = new ByteArrayOutputStream();
			ConverterProperties converterProperties = new ConverterProperties();

			HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

			byte[] bytes = target.toByteArray();

			FileOutputStream out = new FileOutputStream("out.pdf");
			out.write(bytes);
			out.close();

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
					.contentType(MediaType.APPLICATION_PDF)
					.body(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}    
}
