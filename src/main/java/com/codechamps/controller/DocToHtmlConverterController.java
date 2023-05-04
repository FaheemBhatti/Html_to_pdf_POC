package com.codechamps.controller;

import java.io.File;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

@Controller
public class DocToHtmlConverterController {
	
	@PostMapping("/doc-to-html")
	public String docToHtml()  {
		try {

			DocumentConverter converter = new DocumentConverter();
			Result<String> result = converter.convertToHtml(new File("/home/faheem/Downloads/helloworld.docx"));
			String html = result.getValue(); // The generated HTML
			Set<String> warnings = result.getWarnings(); // Any warnings during convers
			return html;
	
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}

}
