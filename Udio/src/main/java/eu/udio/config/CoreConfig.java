package eu.udio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import eu.udio.core.service.UploadService;
import eu.udio.core.service.fs.FileSystemUploadService;

@Configuration
@PropertySource("classpath:eu/udio/config/core_settings.props")
public class CoreConfig {
	@Value("${storagePath}")
	String storagePath;
	
	@Bean
	 public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	 }
	
	@Bean
	public UploadSettings uploadSettings(){
		UploadSettings settings = new UploadSettings();
		settings.setStorageURI(storagePath);
		return settings;
	}
	
	@Bean
	public UploadService uploadService(){
		return new FileSystemUploadService(uploadSettings());
	}
}
