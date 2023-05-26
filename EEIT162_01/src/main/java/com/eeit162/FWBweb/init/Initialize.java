package com.eeit162.FWBweb.init;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class Initialize implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ServletContext servletContext = ((WebApplicationContext) event.getApplicationContext()).getServletContext();
		servletContext.setAttribute("root", servletContext.getContextPath());
		servletContext.setAttribute("webName", "約炮大聯盟");
	}
}
