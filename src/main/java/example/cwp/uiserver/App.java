package example.cwp.uiserver;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import example.cwp.uiserver.multipart.FileStorageService;

@SpringBootApplication
@Configuration
public class App implements CommandLineRunner {
	
	@Resource
	private FileStorageService storageService;
	
    public static void main( String[] args ) {
    	SpringApplication.run(App.class, args);
    }
    
    @Override
	public void run(String... args) throws Exception {
    	System.out.println("########## UI Server is started.");	
    	ProjectManager.getManager().loadProjects();
    	
    	storageService.deleteAll();
        storageService.init();
	}
}