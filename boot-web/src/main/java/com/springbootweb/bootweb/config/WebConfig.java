package com.springbootweb.bootweb.config;

import com.springbootweb.bootweb.entity.Person;
import com.springbootweb.bootweb.formatter.PersonFormatter;
import com.springbootweb.bootweb.interceptor.AnotherInterceptor;
import com.springbootweb.bootweb.interceptor.GreethingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * XML Converter
     */
    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan(Person.class.getPackage().getName());
        return jaxb2Marshaller;
    }

    /**
     * Spring Interceptor
     * 1. .order(orderNumber) / Interceptor의 순서 명시
     * 2. .addPathPatterns(path) / Interceptor의 Path Pattern 명시
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GreethingInterceptor()).order(0);
        registry.addInterceptor(new AnotherInterceptor()).addPathPatterns("/hi").order(-1);
    }

    /**
     * Spring ResourceHandler
     * 1. .addResourceHandler() / UrlMapping
     * 2. .addResourceLocations() / static location
     * 3. .setCacheControl(CacheControl.[option]) / 캐시 사용 및 옵션 설정 => .resourceChain(boolean) / 캐시 사용유무
     * 4. .addResolver() / 요청에 해당하는 리소스를 찾는 방법
     * 5. addTransformer() / 응답으로 보낼 리소스를 변경하는 방법
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mobile/**")
                .addResourceLocations("classpath:/mobile/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
    }

    /**
     * Spring Formmatters
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new PersonFormatter());
    }
}
