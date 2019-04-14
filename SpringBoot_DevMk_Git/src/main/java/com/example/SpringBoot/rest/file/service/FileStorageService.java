package com.example.SpringBoot.rest.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringBoot.exception.FileStorageException;
import com.example.SpringBoot.exception.MyFileNotFoundException;
import com.example.SpringBoot.io.BbsVO;
import com.example.SpringBoot.io.FileVO;
import com.example.SpringBoot.io.SearchVO;
import com.example.SpringBoot.rest.file.mapper.FileMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletContext;

@Service
public class FileStorageService {
	 
	private final Path fileStorageLocation;
	 
	@Autowired
	private FileMapper<SearchVO, Integer, FileVO> fileMapper;
	
    @Autowired
    public FileStorageService(ServletContext context) {
        this.fileStorageLocation = Paths.get(context.getRealPath("/WEB-INF/uploaded"))
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    
    public String storeFile(MultipartFile file) {
    	
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            
            //System.out.println("@@@@@@ targetLocation : "+targetLocation.toString());
            
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
            
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    
    public Resource loadFileAsResource(String fileName) {
    	
    	//System.out.println("fileName : "+fileName.toString());
    	
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
    
	public int fileInsert(FileVO fileVO) throws Exception{
		
		int bbsRestIdx = fileMapper.selectMaxRestBbsIdx();
		
		System.out.println(" file Service @@@@@ bbsRestIdx : "+bbsRestIdx);
		
		fileVO.setRestBbsIdx(bbsRestIdx);
		
		return fileMapper.fileInsert(fileVO);
	}


}
