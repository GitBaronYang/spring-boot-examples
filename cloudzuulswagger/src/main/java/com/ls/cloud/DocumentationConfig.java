package com.ls.cloud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
	
	List<SwaggerResource> resources = new ArrayList<>();
	
	public Boolean addDefaultStatus=false;
	@Override
	public List<SwaggerResource> get() {
		if(!addDefaultStatus){
			resources.add(swaggerResource("lscloudorganize", "/lscloudorganize/v2/api-docs", "2.0"));
			resources.add(swaggerResource("lscloudequipment", "/lscloudequipment/v2/api-docs", "2.0"));
			resources.add(swaggerResource("lsclouduser", "/lsclouduser/v2/api-docs", "2.0"));
			resources.add(swaggerResource("lscloudauth", "/lscloudauth/v2/api-docs", "2.0"));
			resources.add(swaggerResource("lscloudlogin", "/lscloudlogin/v2/api-docs", "2.0"));
			resources.add(swaggerResource("lscloudpost", "/lscloudpost/v2/api-docs", "2.0"));
			//resources.add(swaggerResource("swagger-service-a", "/swagger-service-a/v2/api-docs", "2.0"));
			addDefaultStatus = true;
		}
		
		return resources;
	}
	
	public void addResources(SwaggerResource swaggerResource) {
		get().add(swaggerResource);
	}

	public SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}
}
