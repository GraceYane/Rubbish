package com.example.shizhan.reggie.config;

import com.example.shizhan.reggie.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
//@ComponentScan("com.example.shizhan.reggie")
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射");
        //配置映射，通过网址可以访问项目里边的 html等静态资源
        //下边这句话是让网址的访问路径去 static路径下找资源
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        //registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        //registry.addResourceHandler("/static/front/**").addResourceLocations("classpath:/static/front/");
    }

    /**
     * 扩展mvc框架的消息转换器,将java对象和json数据来回转换
     * 总步骤：1、cv一个转换的工具类JacksonObjectMessage   2、进行add   converters.add(); 一共就两个步骤
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter() ;
        //设置对象转换器，底层使用Jackson将Java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到MVC框架的转换器集合中
        converters.add(0,messageConverter);
        ///            index为0，将这个转换器放在最靠前的位置
    }
}
