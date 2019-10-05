package com.xyer.common.utils;

import com.xyer.common.annotations.ToStringInclude;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.lang.reflect.Field;

public class ObfuscatingToStringBuilder extends ReflectionToStringBuilder
{
    private String obfuscated = "*****";

    public static String toString (final Object object)
    {
        return toString (object, null, false, false, null);
    }

    public static String toString (final Object object,
                                   final ToStringStyle toStringStyle)
    {
        return toString (object, toStringStyle, false, false, null);
    }

    public static String toString (final Object object,
                                   final ToStringStyle toStringStyle,
                                   final boolean outputTransients)
    {
        return toString (object, toStringStyle, outputTransients, false, null);
    }

    public static String toString (final Object object,
                                   final ToStringStyle toStringStyle,
                                   final boolean outputTransients,
                                   final boolean outputStatics)
    {
        return toString (object, toStringStyle, outputTransients, outputStatics, null);
    }

    public static <T> String toString (final T object,
                                       final ToStringStyle toStringStyle,
                                       final boolean outputTransients,
                                       final boolean outputStatics,
                                       final Class <? super T> reflectUpToClass)
    {
        return new ObfuscatingToStringBuilder (object, toStringStyle, null, reflectUpToClass, outputTransients, outputStatics)
                .toString ();
    }

    public static <T> String toString (final T object,
                                       final ToStringStyle toStringStyle,
                                       final boolean outputTransients,
                                       final boolean outputStatics,
                                       final boolean excludeNullValues,
                                       final Class <? super T> reflectUpToClass)
    {
        return new ObfuscatingToStringBuilder (object, toStringStyle, null, reflectUpToClass, outputTransients, outputStatics, excludeNullValues)
                .toString ();
    }

    public static String toStringExclude (final Object object, final String... excludeFieldNames)
    {
        return new ObfuscatingToStringBuilder (object).setExcludeFieldNames (excludeFieldNames).toString ();
    }

    public ObfuscatingToStringBuilder (final Object object)
    {
        super (object);
    }

    public ObfuscatingToStringBuilder (final Object object,
                                       final ToStringStyle toStringStyle)
    {
        super (object, toStringStyle);
    }

    public ObfuscatingToStringBuilder (final Object object,
                                       final ToStringStyle toStringStyle,
                                       final StringBuffer buffer)
    {
        super (object, toStringStyle, buffer);
    }

    public <T> ObfuscatingToStringBuilder (final T object,
                                           final ToStringStyle toStringStyle,
                                           final StringBuffer buffer,
                                           final Class <? super T> reflectUpToClass,
                                           final boolean outputTransients,
                                           final boolean outputStatics)
    {
        super (object, toStringStyle, buffer, reflectUpToClass, outputTransients, outputStatics);
    }

    public <T> ObfuscatingToStringBuilder (final T object,
                                           final ToStringStyle toStringStyle,
                                           final StringBuffer buffer,
                                           final Class <? super T> reflectUpToClass,
                                           final boolean outputTransients,
                                           final boolean outputStatics,
                                           final boolean excludeNullValues)
    {
        super (object, toStringStyle, buffer, reflectUpToClass, outputTransients, outputStatics, excludeNullValues);
    }

    public String getObfuscated ()
    {
        return obfuscated;
    }

    public void setObfuscated (final String obfuscated)
    {
        this.obfuscated = obfuscated;
    }

    @Override
    protected Object getValue (final Field field) throws IllegalAccessException
    {
        final Object value = field.get (this.getObject ());

        if (field.isAnnotationPresent (ToStringInclude.class))
        {
            return value;
        }

        return value == null ? null : obfuscated;
    }
}