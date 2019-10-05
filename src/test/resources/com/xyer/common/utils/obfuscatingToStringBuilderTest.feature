Feature: Obfuscate attributes in an object when calling to string

  Scenario: As a user, I want to be able to obfuscate attributes in an object for masking sensitive information when logging
    Given: An object with fields marked by an annotation
    When: I use ObfuscatingToStringBuilder with recursive default style
    Then: I should have a string made by the object with unmarked fields obfuscated in default style

  Scenario: As a user, I want to be able to obfuscate attributes in an object in JSON format for masking sensitive information when logging
    Given: An object with fields marked by an annotation
    When: I use ObfuscatingToStringBuilder with recursive JSON style
    Then: I should have a string made by the object with unmarked fields obfuscated in JSON style