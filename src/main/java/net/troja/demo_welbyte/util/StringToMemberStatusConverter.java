package net.troja.demo_welbyte.util;

import net.troja.demo_welbyte.beans.MemberStatus;
import org.springframework.core.convert.converter.Converter;

public class StringToMemberStatusConverter implements Converter<String, MemberStatus> {
    public MemberStatus convert(String source) {
        try {
            return MemberStatus.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}