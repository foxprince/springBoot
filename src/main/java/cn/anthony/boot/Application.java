package cn.anthony.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
// same as @Configuration @EnableAutoConfiguration @ComponentScan
// 你可以使用@EntityScan注解自定义实体扫描路径。具体参考Section 67.4, “Separate @Entity definitions
// from Spring configuration”。
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(Application.class);
    }

    public static void main(String[] args) {
	// SpringApplication.run(Application.class, args);

	SpringApplication app = new SpringApplication(Application.class);
	// app.setWebEnvironment(true);
	// app.setApplicationContextClass(AnnotationConfigEmbeddedWebApplicationContext.class);
	// app.setShowBanner(false);
	// app.setSources(sources);
	app.run(args);
    }

}