package ru.jadae.loanprocessor.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.jadae.loanprocessor.enums.FamilyStatus;

@Component
public class StringToFamilyStatusConverter implements Converter<String, FamilyStatus> {

    @Override
    public FamilyStatus convert(String source) {
        try {
            return FamilyStatus.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
