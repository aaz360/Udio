package eu.udio.web.controller;


//import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import eu.udio.config.CoreConfig;
import eu.udio.config.WebConfig;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {CoreConfig.class,WebConfig.class })

public class SiteIntegrationTest {
	private static final String FORWARDED_URL = "/WEB-INF/views/home.html";
	private static final String VIEW = "/home";
	private static final String MAIN_URL = "/WEB-INF/views/main.html";    
    private static final String MAIN_VIEW = "/main";
		
	MockMvc mockMvc;
	@InjectMocks
	SiteController controller;
		
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver())
        		.build();
    }
	
    private InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
    
    
	@Test
    public void rootUrlforwardsCorrectly() throws Exception {
        mockMvc.perform(get("/"))        
        .andExpect(status().isOk())
        .andExpect(view().name(VIEW))
        .andExpect(forwardedUrl(FORWARDED_URL));
    }
		
	@Test
	public void mainPageForwardsCorrectly() throws Exception {
		mockMvc.perform(get("/main"))
		.andExpect(status().isOk())
        .andExpect(view().name(MAIN_VIEW))
        .andExpect(forwardedUrl(MAIN_URL));
	}
}
