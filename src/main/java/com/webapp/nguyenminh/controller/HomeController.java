package com.webapp.nguyenminh.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.nguyenminh.entity.ImageEntity;
import com.webapp.nguyenminh.entity.InformationCtyDataEntity;
import com.webapp.nguyenminh.entity.MenuEntity;
import com.webapp.nguyenminh.entity.SliderHomeEntity;
import com.webapp.nguyenminh.entity.SubMenuEntity;
import com.webapp.nguyenminh.service.InformationCtyService;
import com.webapp.nguyenminh.service.MenuService;
import com.webapp.nguyenminh.service.SliderHomeService;
import com.webapp.nguyenminh.service.SubMenuService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Service
@RestController
@RequestMapping("/api/v1/home")
public class HomeController {
	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	InformationCtyService inforservice;
	@Autowired
	MenuService menuServices;
	@Autowired
	SubMenuService submenuServices;
	@Autowired
	SliderHomeService sliderService;
	@Autowired
	ObjectMapper mapper;

	// ------------------------------------------Load information----------------------------------------//
	@GetMapping(value = "/information")
	public ResponseEntity<?> loadAllInformation() {
		logger.info("load informationcty");
		InformationCtyDataEntity listinfor = inforservice.loadinfor();
		return new ResponseEntity<InformationCtyDataEntity>(listinfor, HttpStatus.OK);
	}

	// ------------------------------------------Load menu----------------------------------------//
	@GetMapping(value = "/menu")
	public ResponseEntity<?> loadMenu() {
		logger.info("load menu");
		List<MenuEntity> listMenu = menuServices.getAll();
		return new ResponseEntity<List<MenuEntity>>(listMenu, HttpStatus.OK);
	}
	// ------------------------------------------Load menu----------------------------------------//
	
	// ------------------------------------------Load Submenu----------------------------------------//
		@GetMapping(value = "/submenu")
		public ResponseEntity<?> loadSubMenu() {
			logger.info("load menu");
			List<SubMenuEntity> listSubMenu = submenuServices.getAll();
			return new ResponseEntity<List<SubMenuEntity>>(listSubMenu , HttpStatus.OK);
		}
	// ------------------------------------------Load slider_home----------------------------------------//
	@GetMapping(value = "/sliderhome")
	public ResponseEntity<?> loadSliderHome() {
		logger.info("load slider");
		List<SliderHomeEntity> listSlider = sliderService.getAllSiderHome();
		return new ResponseEntity<List<SliderHomeEntity>>(listSlider, HttpStatus.OK);
	}

	// --------------------------------------------insert menu------------------------------------------//
	@PostMapping(value = "/insertmenu")
	public ResponseEntity<?> addMenu(@RequestBody MenuEntity[] menu) {
		logger.info("insert menu");
		for(int i = 0 ; i < menu.length;i++) {
			 menuServices.saveMenu(menu[i]);
		}
		return new ResponseEntity<MenuEntity[]>(menu, HttpStatus.OK);
	}
	
	// --------------------------------------------insert Submenu------------------------------------------//
	@PostMapping(value = "/insertsubmenu")
	public ResponseEntity<?> addSubMenu(@RequestBody SubMenuEntity submenu) {
		logger.info("insert submenu");
		SubMenuEntity insertsubmenu = submenuServices.saveSubmenu(submenu);
		return new ResponseEntity<SubMenuEntity>(insertsubmenu, HttpStatus.OK);
	}
	// --------------------------------------------insert slider------------------------------------------//
	@PostMapping(value = "/insertsliderhome")
	public ResponseEntity<?> insertslider(@RequestParam(name = "data") String sliderData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("insert sliederhome");
		
			SliderHomeEntity slderrequest = mapper.readValue(sliderData, SliderHomeEntity.class);
			if (file != null) {
				saveImageSilder(file, slderrequest);
			}
			sliderService.save(slderrequest);
			return new ResponseEntity<SliderHomeEntity>(slderrequest, HttpStatus.OK);
		
	}

	// ------------------------------------------Update InforCty----------------------------------------//
	@PutMapping(value = "/updateinforcty/{serId}")
	public ResponseEntity<?> updateInfor(@PathVariable("serId")  String serId, @RequestParam(name = "data") String inforData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("update infor");
		InformationCtyDataEntity inforreques = mapper.readValue(inforData,InformationCtyDataEntity.class);
		final InformationCtyDataEntity ien = inforservice.getInforCty(serId).get();
		ien.setTitle(inforreques.getTitle());
		ien.setName(inforreques.getName());
		ien.setEmail(inforreques.getEmail());
		ien.setPhone(inforreques.getPhone());
		ien.setDiachi(inforreques.getDiachi());
		ien.setDescription(inforreques.getDescription());
		ien.setCreatedatatime(inforreques.getCreatedatatime());
		if(file != null) {
			saveImageinfor(file, ien);
		}
		inforservice.saveIinformationCty(ien);
		return new ResponseEntity<InformationCtyDataEntity>(ien, HttpStatus.OK);
	}
	
	// ------------------------------------------Update Menu----------------------------------------//
	@PutMapping(value = "/updatemenu/{serId}")
	public ResponseEntity<?> updapeMenu(@PathVariable("serId") Long serId,@RequestBody MenuEntity menu){
		
		MenuEntity menutity = menuServices.getIdMenu(serId).get();
		System.out.println(menutity);
		menutity.setName(menu.getName());
		menutity.setTo1(menu.getTo1());
		menutity.setSubmenu(menu.getSubmenu());
		menuServices.saveMenu(menutity);
		return new ResponseEntity<MenuEntity>(menutity,HttpStatus.OK);
		
	}
	
	// ------------------------------------------Update SliderHome----------------------------------------//
	@PutMapping(value = "/updateslider/{serId}")
	public ResponseEntity<?> updateslider(@PathVariable("serId") String serId, @RequestParam(name = "data") String sliderData,
			@RequestParam(name = "file", required = false) MultipartFile file) 
			throws JsonMappingException,JsonProcessingException{
		logger.info("update slider");
		SliderHomeEntity sliderquest = mapper.readValue(sliderData,SliderHomeEntity.class);
		final SliderHomeEntity ien = sliderService.getById(serId).get();
		ien.setTitle(sliderquest.getTitle());
		ien.setContent(sliderquest.getContent());
		if(file !=null) {
			saveImageSilder(file, ien);
		}
		sliderService.save(ien);
		return new ResponseEntity<SliderHomeEntity>(ien, HttpStatus.OK);
	}
//	delete menu
	@DeleteMapping(value = "deletemenu/{serId}")
	public ResponseEntity<?> deleteMenu(@PathVariable Long serId){
		menuServices.deleteMenu(serId);
		return new ResponseEntity<String>("deletemenu ok",HttpStatus.OK);
	}
//	delete submenu
	@DeleteMapping(value = "deletesubmenu/{serId}")
	public ResponseEntity<?> deleteSubmenu(@PathVariable Long serId){
		submenuServices.deleteSubmenu(serId);
		return new ResponseEntity<String>("delete ok",HttpStatus.OK);
	}

	
	// save image infor
		private void saveImageinfor(MultipartFile file, InformationCtyDataEntity sli) {
			// TODO Auto-generated method stub
			logger.info("save image");
			String name = StringUtils.cleanPath(file.getOriginalFilename());
			if (name.contains("..")) {
				logger.error("error for path of file");
			}
			try {
				ImageEntity imgslider;
				if (sli.getImg() == null) {
					ImageEntity imgEn = new ImageEntity();
					imgEn.setFileName(name);
					imgEn.setFileType(file.getContentType());
					imgEn.setData(file.getBytes());
					imgslider = sliderService.saveImg(imgEn);
					sli.setImg(imgslider);
				} else {
					imgslider = sli.getImg();
					imgslider.setFileName(name);
					imgslider.setFileName(file.getContentType());
					imgslider.setData(file.getBytes());
					imgslider = sliderService.saveImg(imgslider);
					sli.setImg(imgslider);
				}
			} catch (Exception e) {
				// TODO: handle exception
				logger.error(e.toString());
			}

		}

	// save image slider
	private void saveImageSilder(MultipartFile file, SliderHomeEntity sli) {
		// TODO Auto-generated method stub
		logger.info("save image");
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if (name.contains("..")) {
			logger.error("error for path of file");
		}
		try {
			ImageEntity imgslider;
			if (sli.getImg() == null) {
				ImageEntity imgEn = new ImageEntity();
				imgEn.setFileName(name);
				imgEn.setFileType(file.getContentType());
				imgEn.setData(file.getBytes());
				imgslider = sliderService.saveImg(imgEn);
				sli.setImg(imgslider);
			} else {
				imgslider = sli.getImg();
				imgslider.setFileName(name);
				imgslider.setFileName(file.getContentType());
				imgslider.setData(file.getBytes());
				imgslider = sliderService.saveImg(imgslider);
				sli.setImg(imgslider);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.toString());
		}

	}

	// ------------------------------------------Load
	// image----------------------------------------//
	@GetMapping(value = "/viewJPG/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getJPGImage(HttpServletResponse response, @PathVariable String fileId) throws IOException {

		logger.info("get JPG Image");
		ImageEntity img = inforservice.findImageById(fileId).get();
		InputStream inputStream = new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}

	@GetMapping(value = "/viewJPEG/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getJPEGImage(HttpServletResponse response, @PathVariable String fileId) throws IOException {

		logger.info("get JPG Image");
		ImageEntity img = inforservice.findImageById(fileId).get();
		InputStream inputStream = new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}

	@GetMapping(value = "/viewPNG/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getPNGImage(HttpServletResponse response, @PathVariable String fileId) throws IOException {

		logger.info("get JPG Image");
		ImageEntity img = inforservice.findImageById(fileId).get();
		InputStream inputStream = new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}

	@GetMapping(value = "/viewGIF/{fileId}}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getGIFImage(HttpServletResponse response, @PathVariable String fileId) throws IOException {

		logger.info("get JPG Image");
		ImageEntity img = inforservice.findImageById(fileId).get();
		InputStream inputStream = new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}

}
