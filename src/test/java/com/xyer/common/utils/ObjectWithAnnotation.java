package com.xyer.common.utils;

import com.xyer.common.annotations.ToStringInclude;
import com.xyer.common.annotations.ToStringObfuscate;

public class ObjectWithAnnotation
{
    @ToStringInclude
    @ToStringObfuscate
    private int [] anIntArray;

    private double aDouble;

    @ToStringInclude
    @ToStringObfuscate
    private String aString;

    @ToStringInclude
    @ToStringObfuscate
    private AnInnerClass anInnerClass;

    private AnotherInnerClass anotherInnerClass;

    public ObjectWithAnnotation ()
    {
        this.anIntArray = new int [] {1, 2, 3, 4, 5};
        this.aDouble = 12.3;
        this.aString = null;
        this.anInnerClass = new AnInnerClass (this.anIntArray, this.aDouble, this.aString);
        this.anotherInnerClass = new AnotherInnerClass (this.anIntArray, this.aDouble, this.aString);
    }

    private class AnInnerClass
    {
        @ToStringInclude
        @ToStringObfuscate
        private int [] anIntArray;

        @ToStringInclude
        @ToStringObfuscate
        private double aDouble;

        private String aString;

        public AnInnerClass (int[] anIntArray, double aDouble, String aString)
        {
            this.anIntArray = anIntArray;
            this.aDouble = aDouble;
            this.aString = aString;
        }
    }

    private class AnotherInnerClass
    {
        private int [] anIntArray;

        @ToStringInclude
        @ToStringObfuscate
        private double aDouble;

        @ToStringInclude
        @ToStringObfuscate
        private String aString;

        public AnotherInnerClass (int[] anIntArray, double aDouble, String aString)
        {
            this.anIntArray = anIntArray;
            this.aDouble = aDouble;
            this.aString = aString;
        }
    }
}
