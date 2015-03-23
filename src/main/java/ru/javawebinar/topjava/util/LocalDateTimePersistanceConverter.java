package ru.javawebinar.topjava.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * User: Alk
 * Date: 23.03.15
 * Time: 17:07
 * To change this template use File | Settings | File Templates.
 */
@Converter
public class LocalDateTimePersistanceConverter implements AttributeConverter<LocalDateTime,Timestamp> {


    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return Timestamp.valueOf(attribute);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData.toLocalDateTime();  //To change body of implemented methods use File | Settings | File Templates.
    }
}
