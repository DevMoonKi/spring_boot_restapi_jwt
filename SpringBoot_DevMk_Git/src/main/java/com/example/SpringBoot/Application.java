package com.example.SpringBoot;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.example.SpringBoot.filter.AccessLogFilter;
import com.example.SpringBoot.interceptor.JwtInterceptor;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * springboot 1.5.10에서 springboot 2.0.0으로 올리면서 한 삽질.
1. SpringBootServletInitializer 패키지 이름이 바뀜
import org.springframework.boot.web.support.SpringBootServletInitializer;
->
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

2. WebMvcConfigurerAdapter deprecated
public class CustomWebMvcConfig extends WebMvcConfigurerAdapter
->
public class CustomWebMvcConfig implements WebMvcConfigurer

3. JPA method  findOne -> findById  ㄴor getOne
ID를 가지고 Domain을 가져오는 method로 findOne을 썻다면 
getOne 또는 findById로 변경.

주의할점 : findById는 return값을 Optional<T>로 리턴. 
 * */


/*
 * WebMvcConfigurer에 있으면@Override 없으면 @Bean
 * 
 * */

@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
public class Application implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	//인터셉터1
	//@Autowired
	//private InterceptorAccess interceptorAccess;
	
	//인터셉터2
	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	//application.properties에 같은 내용으로 설정 가능하다.
/*	JSP 설정
	@Bean
	public InternalResourceViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
*/	
/* templates(HTML)의 기본 경로는 src/resource/templates 임 아래는 /WEB-INF/로 경로를 잡는 방법이다.
	@Bean
	public SpringResourceTemplateResolver templateResolver(){
	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setPrefix("/WEB-INF/templates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    templateResolver.setCacheable(true);
	    return templateResolver;
	}
*/	
	/**
	 * AccessLog filter 설정
	 * @return
	 */
	@Bean
	public FilterRegistrationBean accessLogFilterBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new AccessLogFilter());
		return registrationBean;
	}
	
	/*
	 * swagger 설정 API문서 제작해줌.
	 * @return
	 */
	//apis는 검색할 Controller를 제한 할 수 있습니다.
	//paths는 검색된 Controller에 매핑 URL를 특정패턴으로 제한할 수 있습니다.
	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
				.paths(PathSelectors.ant("/rest/**")) // 그중 /api/** 인 URL들만 필터링
				.build();
	}
	
	/**
	 * 세션 및 쿠기 locale 설정
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
		// 세션 기준으로 로케일을 설정한다.
		//SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		// 쿠키기준(세션이 끊겨도 브라우저에 설정된 쿠기기준)
		// CookieLocaleResolver localeResolver2=new CookieLocaleResolver();
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.KOREA);
		return localeResolver;
	}
	
	/**
	 * parameter:lang 인터셉터 설정
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		// request로 넘어오는 language parameter를 받아서 locale로 설정한다.
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	
	
	/*
	 * 
	 * 	@Autowired private InterceptorAccess interceptorAccess;
	 * 사용하려면 Bean을 동록해야 한다. 아니면 null에러가 뜬다.
		@Bean
		public InterceptorAccess interceptorAccess() {
			return new InterceptorAccess();
		}
	 * */
	
	//인터셉터 예외 url
    private static final String[] EXCLUDE_PATHS = {
            "/rest/login/**",
    };
	
	/**
	 * 인터셉터 등록
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(jwtInterceptor)
		.addPathPatterns("/rest/**")
		.excludePathPatterns(EXCLUDE_PATHS);
		//registry.addInterceptor(interceptorAccess).addPathPatterns("/rest/**");
	}
	
	/**
	 * 크로스 도메인
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET","POST","PUT","DELETE")
				.allowedHeaders("Content-Type","X-Requested-With","accept","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials")
				.allowCredentials(true).maxAge(10);
	}

}
