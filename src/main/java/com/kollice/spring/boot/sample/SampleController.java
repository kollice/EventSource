package com.kollice.spring.boot.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kollice on 2016/4/1.
 */
@Controller
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan
public class SampleController extends WebMvcConfigurerAdapter{
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("WEB-INF/page/");
        resolver.setSuffix(".html");
        return resolver;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello() {
        return new ModelAndView("push");
    }

    @RequestMapping(value = "/push", method = RequestMethod.GET,produces = "text/event-stream")
    @ResponseBody
    public String push(HttpServletResponse response) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//            String date = sdf.format(new Date());
//
//            response.setContentType("text/event-stream"); // 设置contentType
////            response.setContentType("application/x-dom-event-stream"); // 设置contentType
//            response.setHeader("Cache-Control", "no-cache"); // 设置不缓存
//            response.setCharacterEncoding("utf-8");
////            response.setHeader("Pragma", "no-cache");
////            response.setDateHeader("Expires", 0);
//            PrintWriter pw = null;
//            pw = response.getWriter();
//
//            pw.print("data: today is " + date + " wish you happy~~~"); // 注意必须以data：开头
//            pw.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String date = sdf.format(new Date());
        return"data:"+date +"\n\n";
    }


    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }
}
