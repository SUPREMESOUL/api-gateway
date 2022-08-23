package com.gateway.comfigure;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration  
@CrossOrigin(origins = "*")
public class ApiGatewayConfiguration {
  
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
	return builder.routes()
			.route(p->p.path("/securityauth/**")
		    		  .filters(f->f.rewritePath("/securityauth/(?<remaining>.*)", "/${remaining}"))
		                  .uri("lb://SECURITYAUTH"))
		      .route(p->p.path("/securityval/**")
		    		  .filters(f->f.rewritePath("/securityval/(?<remaining>.*)", "/${remaining}"))
		                  .uri("lb://SECURITYAUTH"))
		      .route(p->p.path("/processdetail/**")
		    		  .filters(f->f.rewritePath("/processdetail/(?<segment>.*)", "/${segment}"))
		                  .uri("lb://COMPONENTPROCESSING"))
		          .build();
	
	/*    .route(p->p.path("/completeprocessing/**")
		    		  .filters(f->f.rewritePath("/completeprocessing/(?<remaining>.*)", "/COMPONENT-PROCESSING-MICROSERVICE/${remaining}"))
		                  .uri("lb://COMPONENT-PROCESSING-MICROSERVICE")) */
	
}

}
