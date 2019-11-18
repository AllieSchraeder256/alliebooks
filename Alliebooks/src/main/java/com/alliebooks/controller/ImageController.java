package com.alliebooks.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alliebooks.service.ImageService;

@Controller
public class ImageController {
	@Autowired
	private ImageService imageService;
	
	//TODO why throw all them exceptions?
	@GetMapping(value = "/fetch-image")
	  public void showImage(@RequestParam("file") String file, HttpServletResponse response,HttpServletRequest request) throws IOException, SQLException {
		
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	    
	    InputStream in = imageService.fetchImage(file);
	    if (in != null) {
	    	IOUtils.copy(in, response.getOutputStream());
	    }
	    response.getOutputStream().close();
	}
}
