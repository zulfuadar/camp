package tr.org.lkd.lyk2015.camp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {WebConfig.class};
	}
	//frontcontroller config classi
	

	@Override
	protected String[] getServletMappings() {

		
		return new String[] {"/"};
	}
	
	

}
