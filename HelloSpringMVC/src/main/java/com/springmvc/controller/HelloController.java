package com.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("Hello")
public class HelloController {

	@RequestMapping("HelloWorld")
	public String HelloWorld(Model model) {
		model.addAttribute("message", "helloworld spring mvc");
		return "HelloWorld";

	}

	// 上传文件的入口方法 返回上传页面
	@RequestMapping("/upload")
	public String upload() {
		return "uploadFile";
	}

	// 上文件的响应方法
	@RequestMapping("/doUpload")
	public String doUploadFile(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(),
						new File("E:\\temp\\file", System.currentTimeMillis() + file.getOriginalFilename()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "success";

	}

	// 上传多个文件的入口方法
	@RequestMapping("/uploadMulti")
	public String showUploadPage2() {
		return "uploadMultifile"; // view文件夹下的上传多个文件的页面
	}

	// 上传多个文件的响应方法
	@RequestMapping("/doMultiUpload")
	public String doMultiUpload(MultipartHttpServletRequest multirequest) {
		Iterator<String> fileNames = multirequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();// 获取文件名
			MultipartFile file = multirequest.getFile(fileName); // 根据文件名获取文件
			if (!file.isEmpty()) {
				try {
//					log.debug("Process file: {}", file.getOriginalFilename());
					FileUtils.copyInputStreamToFile(file.getInputStream(),
							new File("E://temp/file2", System.currentTimeMillis() + file.getOriginalFilename()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return "success";

	}
}
