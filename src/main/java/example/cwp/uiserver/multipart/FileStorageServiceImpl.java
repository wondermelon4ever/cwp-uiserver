package example.cwp.uiserver.multipart;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {
	
	private final Path root = Paths.get("D:\\Source\\Workplaces\\Workspace-nifi-test\\uiserver\\src\\main\\resources\\static\\images");

	@Override
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Override
	public String save(MultipartFile file) {
		String originalFileName = file.getOriginalFilename();
//		String path = this.root.toString() + File.separator + originalFileName;
//		System.out.println("PATH =====> " + path);
		try {
			String fileName = UUID.randomUUID().toString()+originalFileName.substring(originalFileName.lastIndexOf("."));
//			String fileName = originalFileName;
//			if(new File(path).exists()) {
//				fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-New" + originalFileName.substring(originalFileName.lastIndexOf("."));
//				System.out.println("FILE-NAME =======>" + fileName);
//			}
			
			Files.copy(file.getInputStream(), this.root.resolve(fileName));
			return fileName;
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}
}
