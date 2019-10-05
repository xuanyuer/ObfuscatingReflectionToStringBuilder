package com.xyer.common.utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class ObfuscatingToStringBuilderTestStepDefinitions
{
    private ObjectWithAnnotation objectWithAnnotation;

    private String result;

    @Given ("^An object with fields marked by an annotation$")
    public void an_object_with_fields_marked_by_annotation ()
    {
        this.objectWithAnnotation = new ObjectWithAnnotation ();
    }

    @When ("^I use ObfuscatingToStringBuilder with recursive default style$")
    public void use_ObfuscatingToStringBuilder_with_default_style ()
    {
        this.result = ObfuscatingToStringBuilder.toString (this.objectWithAnnotation, ObfuscatingRecursiveToStringStyle.DEFAULT_STYLE);
    }

    @When ("^I use ObfuscatingToStringBuilder with recursive JSON style$")
    public void use_ObfuscatingToStringBuilder_with_json_style ()
    {
        this.result = ObfuscatingToStringBuilder.toString (this.objectWithAnnotation, ObfuscatingRecursiveToStringStyle.RECURSIVE_JSON_STYLE);
    }

    @Then ("^I should have a string made by the object with unmarked fields obfuscated in default style$")
    public void unmarked_fields_should_be_obfuscated_in_default_style ()
    {
        assertThat (this.result).isEqualTo
        (
            "com.xyer.commons.utils.ObjectWithAnnotation "                                  +
            "["                                                                             +
                "anIntArray = {1,2,3,4,5}, "                                                +
                "aDouble = *****, "                                                         +
                "aString = <null>, "                                                        +
                "anInnerClass = com.xyer.commons.utils.ObjectWithAnnotation$AnInnerClass "  +
                "["                                                                         +
                    "anIntArray = {1,2,3,4,5}, "                                            +
                    "aDouble = 12.3, "                                                      +
                    "aString = <null>"                                                      +
                "], "                                                                       +
                "anotherInnerClass = *****"                                                 +
            "]"
        );
    }

    @Then ("^I should have a string made by the object with unmarked fields obfuscated in JSON style$")
    public void unmarked_fields_should_be_obfuscated_in_json_style ()
    {
        assertThat (this.result).isEqualTo
        (
            "{"                                         +
                "\"anIntArray\" : [1,2,3,4,5], "        +
                "\"aDouble\" : \"*****\", "             +
                "\"aString\" : null, "                  +
                "\"anInnerClass\" : "                   +
                "{"                                     +
                    "\"anIntArray\" : [1,2,3,4,5], "    +
                    "\"aDouble\" : 12.3, "              +
                    "\"aString\" : null"                +
                "}, "                                   +
                "\"anotherInnerClass\" : \"*****\""     +
            "}"
        );
    }
}
