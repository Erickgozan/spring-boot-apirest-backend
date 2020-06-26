package com.erickgozan.springboot.apirest.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	public String uploadFile(MultipartFile archivo) throws IOException;

	public boolean isExistFile(String nombreArchivoAnterior);

	public Resource viewResource(String nombreArchivo) throws MalformedURLException;

	public Path rutaArchivo(String nombreArchivo);

}
