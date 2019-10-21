package com.pms.config.mvc;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.pms.config.sitemesh.WebSiteMeshFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

import static org.springframework.web.cors.CorsConfiguration.ALL;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${attach_path}")
    private String attachPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/attach/**").
                addResourceLocations("file:" + attachPath);
    }


    /**
     * 装饰器
     */
    @Bean
    public FilterRegistrationBean siteMeshFilter() {
        FilterRegistrationBean fitler = new FilterRegistrationBean();
        WebSiteMeshFilter siteMeshFilter = new WebSiteMeshFilter();
        fitler.setFilter(siteMeshFilter);
        return fitler;
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar(){
        return new MyErrorPageRegistrar();
    }

    private static class MyErrorPageRegistrar implements ErrorPageRegistrar {
        @Override
        public void registerErrorPages(ErrorPageRegistry registry) {
            registry.addErrorPages(
                    new ErrorPage(HttpStatus.BAD_REQUEST, "/400.html"),
                    new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html"),
                    new ErrorPage(HttpStatus.NOT_FOUND, "/404.html")
            );
        }

    }

    /**
     * 提前配置跨越问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ALL)
                .allowedMethods(ALL)
                .allowedHeaders(ALL)
                .allowCredentials(true);
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fjc = new FastJsonConfig();
        //1、序列化重点
        fjc.setSerializerFeatures(SerializerFeature.BrowserCompatible);
        fastJsonConverter.setFastJsonConfig(fjc);
        converters.add(fastJsonConverter);
    }
}
