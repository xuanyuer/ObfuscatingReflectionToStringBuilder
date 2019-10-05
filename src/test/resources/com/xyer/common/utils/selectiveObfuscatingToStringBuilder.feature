Feature: Selectively obfuscate attributes in an object when calling the toString() method

  Scenario: As a user, I want to be able to selectively obfuscate attributes in an object for masking sensitive information when logging
    Given: An object with fields marked by obfuscate annotation
    When: I use SelectiveObfuscatingToStringBuilder with recursive default style
    Then: I should have a string made by the object with marked fields obfuscated in default style

  Scenario: As a user, I want to be able to selectively obfuscate attributes in an object in JSON format for masking sensitive information when logging
    Given: An object with fields marked by obfuscate annotation
    When: I use SelectiveObfuscatingToStringBuilder with recursive JSON style
    Then: I should have a string made by the object with marked fields obfuscated in JSON style