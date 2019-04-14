package com.example.SpringBoot.rest.bbs.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;

import com.example.SpringBoot.common.CrudService;
import com.example.SpringBoot.exception.ExceptionController;
import com.example.SpringBoot.io.BbsVO;
import com.example.SpringBoot.io.ResponseVO;
import com.example.SpringBoot.io.SearchVO;
import com.example.SpringBoot.io.FileVO;
import com.example.SpringBoot.rest.file.service.FileStorageService;

import io.swagger.annotations.ApiModelProperty;

/*
@RestController 어노테이션을 통해 컨트롤러를 restful로 정의해줍니다.
이렇게 될 경우 @ResponseBody가 필요 없어집니다.
URL정
GET	index/retrieve	모든/특정 리소스를 조회
POST	create	리소스를 생성
PUT	update	리소스를 갱신
DELETE	delete	리소스를 삭제
@RequestBody 는 { "column" : "1"} 형태의 json을 받는다.
@RequestParam은 post나 get파라미터를 받는
*/

@RestController
@RequestMapping(value = "/rest")
public class BbsController{	
	
	private static final Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
	 private FileStorageService fileStorageService;
	
	@Autowired
	CrudService< SearchVO, Integer, BbsVO > crudService;
	
	/**
	 * 게시판 전체조회
	 * @param searchVO
	 * @return resp
	 * @throws Exception
	 */
	@ApiModelProperty(value="", notes="게시판 리스트 조회")
	@PostMapping("/bbs/list")
	public ResponseVO<List<BbsVO>> getSelectRestBbsList(@RequestBody SearchVO searchVO) throws Exception{
		ResponseVO<List<BbsVO>> resp = new ResponseVO<>();
		//list
		resp.setResponse(crudService.selectList(searchVO)); 
		//count
		resp.setResponseRowCnt(crudService.selectCount(searchVO)); 
		return resp;
	}
	
	/**
	 * 게시판 등록
	 * @param bbsVO
	 * @return resp
	 * @throws Exception
	 */
	@ApiModelProperty(value="", notes="게시판 등록")
	//@PostMapping(path = "/bbs", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@PostMapping("/bbs")
	public ResponseVO<Object> setInsertRestBbs(@ModelAttribute("bbsVO") BbsVO bbsVO) throws Exception{
		
		FileVO fileVO = new FileVO();
		ResponseVO<Object> resp = new ResponseVO<>();
		
		if(bbsVO==null || bbsVO.toString() == "") {
			resp.setMessage("error");
			resp.setCheck(false);
			return resp;
			
		} else {
			
			crudService.insert(bbsVO);   
			String fileName = fileStorageService.storeFile(bbsVO.getUpFile());
			
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/downloadFile/")
					.path(fileName)
					.toUriString();
			
			fileVO.setMemberIdx(bbsVO.getMemberIdx());
			fileVO.setFileName(fileName);
			fileVO.setFilePath(fileDownloadUri);
			fileVO.setFileType(bbsVO.getUpFile().getContentType());
			fileVO.setFileSize(bbsVO.getUpFile().getSize());
			
			fileStorageService.fileInsert(fileVO);
			
			
/*			
			if (!bbsVO.getUpFile().isEmpty()) {
	        	   
				try {

					String oriFileNm = bbsVO.getUpFile().getOriginalFilename();
					File destinationFile = new File(context.getRealPath("/WEB-INF/uploaded" ) + File.separator + oriFileNm);
					bbsVO.getUpFile().transferTo(destinationFile );

					fileVO.setFileName(destinationFile .getPath());
					fileVO.setFileSize(bbsVO.getUpFile().getSize());
					
					resp.setMessage("ile Uploaded Successfully");
					resp.setStatusCode(HttpStatus.OK);
					return resp;

				} catch (Exception e ) {
					e.printStackTrace();
					resp.setStatusCode(HttpStatus.BAD_REQUEST);
	                return resp;
				}
			}
*/			
			
			return resp;
			
		}
		
		
	}
	
	/**
	 * 게시판 상세조회
	 * @param seq
	 * @return resp
	 * @throws Exception
	 */
	@ApiModelProperty(value="", notes="게시판 상세조회")
	@GetMapping("/bbs/{seq}")	 //single item
	public ResponseVO<?> getSelectRestBbsDetail(@PathVariable("seq") int seq) throws Exception {
		ResponseVO<BbsVO> resp = new ResponseVO<>();
		resp.setResponse(crudService.selectOne(seq));
		return resp;
	}
	
	/**
	 * 게시판 수정
	 * @param bbsVO
	 * @return resp
	 * @throws Exception
	 */
	@ApiModelProperty(value="", notes="게시판 수정")
	@PutMapping("/bbs") //update @PathVariable("seq") int seq, 
	public ResponseVO<?> setUpdateRestBbs(@RequestBody BbsVO bbsVO) throws Exception {
		ResponseVO<?> resp = new ResponseVO<>();
		if(crudService.update(bbsVO) != 1) {
			resp.setCheck(false);
		}
		return resp;
	}
	
	/**
	 * 게시판 삭제
	 * @param bbsVO
	 * @return resp
	 * @throws Exception
	 */
	@ApiModelProperty(value="", notes="게시판 삭제")
	@DeleteMapping("/bbs") //delete @PathVariable("seq") int seq,
	public ResponseVO<?> setDeleteRestBbs(@RequestBody BbsVO bbsVO) throws Exception {
		ResponseVO<?> resp = new ResponseVO<>();
		if(crudService.delete(bbsVO) != 1) {
			resp.setCheck(false);
		}
		return resp;
	}
	
	/*파일 다운로드*/
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
