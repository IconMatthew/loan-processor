package ru.jadae.loanprocessor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.jadae.loanprocessor.converter.StringToGenderConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToGenderConverter stringToGenderConverter;

    public WebConfig(StringToGenderConverter stringToGenderConverter) {
        this.stringToGenderConverter = stringToGenderConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToGenderConverter);
    }
}
