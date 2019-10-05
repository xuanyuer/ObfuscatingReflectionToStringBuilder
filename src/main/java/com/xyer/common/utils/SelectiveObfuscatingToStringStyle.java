package com.xyer.common.utils;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.text.StringEscapeUtils;

public class SelectiveObfuscatingToStringStyle
{
    public static final DefaultStyle DEFAULT_STYLE = new DefaultStyle ();

    public static final RecursiveJsonStyle RECURSIVE_JSON_STYLE = new RecursiveJsonStyle ();

    private static final String FIELD_NAME_QUOTE = "\"";

    /**
     * This class is an extension of {@link RecursiveToStringStyle} but uses {@link SelectiveObfuscatingToStringBuilder} instead
     * to build object strings.
     *
     * */
    private static final class DefaultStyle extends RecursiveToStringStyle
    {
        private DefaultStyle ()
        {
            super ();

            this.setUseIdentityHashCode (false);

            this.setContentStart (" [");

            this.setFieldSeparator (", ");
            this.setFieldNameValueSeparator (" = ");
        }

        @Override
        public void appendDetail (StringBuffer buffer, String fieldName, Object value)
        {
            if (!ClassUtils.isPrimitiveWrapper (value.getClass ()) &&
                !String.class.equals (value.getClass ()) &&
                accept (value.getClass ()))
            {
                buffer.append (SelectiveObfuscatingToStringBuilder.toString (value, this));
            }
            else
            {
                super.appendDetail (buffer, fieldName, value);
            }
        }
    }

    /**
     * Combines the functionality of {@link RecursiveToStringStyle} with the JSON_STYLE available in {@link ToStringStyle}.
     * This produces an output of {@link RecursiveToStringStyle} but in a valid JSON format.
     * This class also uses {@link SelectiveObfuscatingToStringBuilder} to build object strings.
     *
     * */
    private static final class RecursiveJsonStyle extends RecursiveToStringStyle
    {
        private RecursiveJsonStyle ()
        {
            super ();

            this.setUseClassName (false);
            this.setUseIdentityHashCode (false);

            this.setContentStart ("{");
            this.setContentEnd ("}");

            this.setArrayStart ("[");
            this.setArrayEnd ("]");

            this.setFieldSeparator (", ");
            this.setFieldNameValueSeparator (" : ");

            this.setNullText ("null");

            this.setSummaryObjectStartText ("\"<");
            this.setSummaryObjectEndText (">\"");

            this.setSizeStartText ("\"<size=");
            this.setSizeEndText (">\"");
        }

        @Override
        public void append (final StringBuffer buffer,
                            final String fieldName,
                            final Object[] array,
                            final Boolean fullDetail)
        {
            validateFieldNameAndFullDetail (fieldName, fullDetail);
            super.append (buffer, fieldName, array, fullDetail);
        }

        @Override
        public void append (final StringBuffer buffer,
                            final String fieldName,
                            final long[] array,
                            final Boolean fullDetail)
        {
            validateFieldNameAndFullDetail (fieldName, fullDetail);
            super.append (buffer, fieldName, array, fullDetail);
        }

        @Override
        public void append (final StringBuffer buffer,
                            final String fieldName,
                            final int[] array,
                            final Boolean fullDetail)
        {
            validateFieldNameAndFullDetail (fieldName, fullDetail);
            super.append (buffer, fieldName, array, fullDetail);
        }

        @Override
        public void append (final StringBuffer buffer,
                            final String fieldName,
                            final short[] array,
                            final Boolean fullDetail)
        {
            validateFieldNameAndFullDetail (fieldName, fullDetail);
            super.append (buffer, fieldName, array, fullDetail);
        }

        @Override
        public void append (final StringBuffer buffer,
                            final String fieldName,
                            final byte [] array,
                            final Boolean fullDetail)
        {
            validateFieldNameAndFullDetail (fieldName, fullDetail);
            super.append (buffer, fieldName, array, fullDetail);
        }

        @Override
        public void append (final StringBuffer buffer,
                            final String fieldName,
                            final char [] array,
                            final Boolean fullDetail)
        {
            validateFieldNameAndFullDetail (fieldName, fullDetail);
            super.append (buffer, fieldName, array, fullDetail);
        }

        @Override
        public void append (final StringBuffer buffer,
                            final String fieldName,
                            final double [] array,
                            final Boolean fullDetail)
        {
            validateFieldNameAndFullDetail (fieldName, fullDetail);
            super.append (buffer, fieldName, array, fullDetail);
        }

        @Override
        public void append (final StringBuffer buffer,
                            final String fieldName,
                            final float[] array,
                            final Boolean fullDetail)
        {
            validateFieldNameAndFullDetail (fieldName, fullDetail);
            super.append (buffer, fieldName, array, fullDetail);
        }

        @Override
        public void append (final StringBuffer buffer,
                            final String fieldName,
                            final boolean[] array,
                            final Boolean fullDetail)
        {
            validateFieldNameAndFullDetail (fieldName, fullDetail);
            super.append (buffer, fieldName, array, fullDetail);
        }

        @Override
        public void append (final StringBuffer buffer,
                            final String fieldName,
                            final Object value,
                            final Boolean fullDetail)
        {
            validateFieldNameAndFullDetail (fieldName, fullDetail);
            super.append (buffer, fieldName, value, fullDetail);
        }

        @Override
        public void appendDetail (final StringBuffer buffer,
                                  final String fieldName,
                                  final Object value)
        {
            if (!ClassUtils.isPrimitiveWrapper (value.getClass ()) &&
                !String.class.equals (value.getClass ()) &&
                accept (value.getClass ()))
            {
                buffer.append (SelectiveObfuscatingToStringBuilder.toString (value, this));
            }
            else
            {
                if (value instanceof String || value instanceof Character)
                {
                    appendValueAsString (buffer, value.toString ());
                    return;
                }

                if (value instanceof Number || value instanceof Boolean)
                {
                    buffer.append (value);
                    return;
                }

                final String valueAsString = value.toString ();
                if (isJsonObject (valueAsString) || isJsonArray (valueAsString))
                {
                    buffer.append (value);
                    return;
                }

                appendDetail (buffer, fieldName, valueAsString);
            }
        }

        @Override
        protected void appendDetail (final StringBuffer buffer,
                                     final String fieldName,
                                     char value)
        {
            appendValueAsString (buffer, String.valueOf (value));
        }

        @Override
        protected void appendFieldStart (final StringBuffer buffer, final String fieldName)
        {
            if (fieldName == null)
            {
                throw new UnsupportedOperationException ("Field names are mandatory when using ObfuscatingRecursiveToStringStyle");
            }

            super.appendFieldStart (buffer, FIELD_NAME_QUOTE +
                                            StringEscapeUtils.escapeJson (fieldName) +
                                            FIELD_NAME_QUOTE);
        }

        private void validateFieldNameAndFullDetail (final String fieldName, final Boolean fullDetail)
        {
            if (fieldName == null)
            {
                throw new UnsupportedOperationException ("Field names are mandatory when using ObfuscatingRecursiveToStringStyle");
            }

            if (!isFullDetail (fullDetail))
            {
                throw new UnsupportedOperationException ("FullDetail must be true when using ObfuscatingRecursiveToStringStyle");
            }
        }

        private boolean isJsonArray (final String valueAsString)
        {
            return valueAsString.startsWith (getArrayStart ()) &&
                   valueAsString.endsWith (getArrayEnd ());
        }

        private boolean isJsonObject (final String valueAsString)
        {
            return valueAsString.startsWith (getContentStart ()) &&
                   valueAsString.endsWith (getContentEnd ());
        }

        private void appendValueAsString (final StringBuffer buffer, final String value)
        {
            buffer.append ('"').append (StringEscapeUtils.escapeJson (value)).append ('"');
        }
    }
}
