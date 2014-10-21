package eu.udio.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import eu.udio.core.service.UploadService;
import eu.udio.core.service.args.UploadFileArgs;
import eu.udio.web.domain.File;
import eu.udio.web.domain.Files;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@Autowired
	UploadService uploadService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Files upload( @RequestParam(value="file[]") MultipartFile fileUpload, @RequestParam String tags) throws Exception{
				
		UploadFileArgs uploadArgs = new UploadFileArgs();
		uploadArgs.inputStream = fileUpload.getInputStream();
		uploadArgs.originalFileName = fileUpload.getOriginalFilename();
		uploadService.uploadFile(uploadArgs);
		
		List<File> files = new ArrayList<File>();
		File file = new File();
		file.setName(fileUpload.getOriginalFilename());
		files.add(file);
		
		Files result = new Files();
		result.files = files;
		
		return result ;
	}
}
