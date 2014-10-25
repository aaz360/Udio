package eu.udio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"eu.udio.web.controller"} )
public class WebConfig extends WebMvcConfigurerAdapter{

	@Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	  }
	
	
	@Bean
	public MultipartResolver multipartResolver(){
		return new CommonsMultipartResolver();
	}
	
	@Bean
	  public ServletContextTemplateResolver templateResolver() {
	    ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
	    resolver.setPrefix("/WEB-INF/views/");
	    resolver.setSuffix(".html");
	    //NB, selecting HTML5 as the template mode.
	    resolver.setTemplateMode("LEGACYHTML5");
	    resolver.setCacheable(false);
	    return resolver;

	  }
		
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine engine = new SpringTemplateEngine();
	    engine.setTemplateResolver(templateResolver());
	    return engine;
	  }
	
	
	 @Bean
	  public ViewResolver viewResolver() {

	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    viewResolver.setOrder(1);
	    viewResolver.setViewNames(new String[]{"*"});
	    viewResolver.setCache(false);
	    return viewResolver;
	  }
}
