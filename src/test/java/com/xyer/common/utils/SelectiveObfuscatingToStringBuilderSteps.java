package com.xyer.common.utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectiveObfuscatingToStringBuilderSteps
{
    private ObjectWithAnnotation objectWithAnnotation;

    private String result;

    @Given ("^An object with fields marked by obfuscate annotation$")
    public void an_object_with_marked_fields ()
    {
        this.objectWithAnnotation = new ObjectWithAnnotation ();
    }

    @When ("^I use SelectiveObfuscatingToStringBuilder with recursive default style$")
    public void use_SelectiveObfuscatingToStringBuilder_with_default_style ()
    {
        this.result = SelectiveObfuscatingToStringBuilder.toString (this.objectWithAnnotation, SelectiveObfuscatingToStringStyle.DEFAULT_STYLE);
    }

    @When ("^I use SelectiveObfuscatingToStringBuilder with recursive JSON style$")
    public void use_SelectiveObfuscatingToStringBuilder_with_json_style ()
    {
        this.result = SelectiveObfuscatingToStringBuilder.toString (this.objectWithAnnotation, SelectiveObfuscatingToStringStyle.RECURSIVE_JSON_STYLE);
    }

    @Then ("^I should have a string made by the object with marked fields obfuscated in default style$")
    public void marked_fields_should_be_obfuscated_in_default_style ()
    {
        assertThat (this.result).isEqualTo
        (
            "com.xyer.commons.utils.ObjectWithAnnotation "                                      +
            "["                                                                                 +
                "anIntArray = *****, "                                                          +
                "aDouble = 12.3, "                                                              +
                "aString = <null>, "                                                            +
                "anInnerClass = ***** "                                                         +
                "anotherInnerClass = com.xyer.commons.utils.ObjectWithAnnotation$AnInnerClass " +
                "["                                                                             +
                    "anIntArray = *****, "                                                      +
                    "aDouble = *****, "                                                         +
                    "aString = <null>"                                                          +
                "], "                                                                           +
            "]"
        );
    }

    @Then ("^I should have a string made by the object with marked fields obfuscated in JSON style$")
    public void marked_fields_should_be_obfuscated_in_json_style ()
    {
        assertThat (this.result).isEqualTo
        (
            "{"                                         +
                "\"anIntArray\" : \"*****\", "          +
                "\"aDouble\" : 12.3, "                  +
                "\"aString\" : null, "                  +
                "\"anInnerClass\" : \"*****\""          +
                "\"anotherInnerClass\" : "              +
                "{"                                     +
                    "\"anIntArray\" : [1,2,3,4,5], "    +
                    "\"aDouble\" : 12.3, "              +
                    "\"aString\" : null"                +
                "}, "                                   +
            "}"
        );
    }
}
