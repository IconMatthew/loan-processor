package ru.jadae.loanprocessor.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.jadae.loanprocessor.enums.Gender;

@Component
public class StringToGenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String source) {
        try {
            return Gender.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
