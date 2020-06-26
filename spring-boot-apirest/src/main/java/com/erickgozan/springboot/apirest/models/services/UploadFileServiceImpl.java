package com.erickgozan.springboot.apirest.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	// private final Logger log = LoggerFactory.getLogger(getClass());

	// Guardar la foto en la base de datos
	@Override
	public String uploadFile(MultipartFile archivo) throws IOException {

		String nombreArchivo = UUID.randomUUID().toString().concat("_").concat(archivo.getOriginalFilename())
				.replace(" ", "_");
		Path rutaArchivo = rutaArchivo(nombreArchivo);
		if (!archivo.isEmpty()) {
			Files.copy(archivo.getInputStream(), rutaArchivo);
		}
		return nombreArchivo;
	}

	// Elimar la foto anterior
	@Override
	public boolean isExistFile(String nombreArchivoAnterior) {

		if (nombreArchivoAnterior != null && nombreArchivoAnterior.length() > 0) {
			Path rutaArchivoAnterior = Paths.get("uploads").resolve(nombreArchivoAnterior).toAbsolutePath();
			File archivoFotoAnterior = rutaArchivoAnterior.toFile();
			if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				return archivoFotoAnterior.delete();
			}
		}
		return false;
	}

	// Visualiza la imagen
	@Override
	public Resource viewResource(String nombreArchivo) throws MalformedURLException {

		Path rutaArchivo = rutaArchivo(nombreArchivo);
		Resource recurso = new UrlResource(rutaArchivo.toUri());

		if (!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-user.png").toAbsolutePath();
			recurso = new UrlResource(rutaArchivo.toUri());
		}

		return recurso;
	}

	// Devuelve la ruta absoluta del archivo ademas de crear la carpeta uplads si no
	// existe
	public Path rutaArchivo(String nombreArchivo) {

		File rutaCarpeta = Paths.get("uploads").toAbsolutePath().toFile();
		if (!rutaCarpeta.exists()) {
			rutaCarpeta.mkdir();
		}
		return rutaCarpeta.toPath().resolve(nombreArchivo);
	}
}
