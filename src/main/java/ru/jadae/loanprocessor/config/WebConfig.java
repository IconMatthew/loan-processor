package ru.jadae.loanprocessor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.jadae.loanprocessor.converter.StringToFamilyStatusConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToFamilyStatusConverter stringToFamilyStatusConverter;

    public WebConfig(StringToFamilyStatusConverter stringToFamilyStatusConverter) {
        this.stringToFamilyStatusConverter = stringToFamilyStatusConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToFamilyStatusConverter);
    }
}
