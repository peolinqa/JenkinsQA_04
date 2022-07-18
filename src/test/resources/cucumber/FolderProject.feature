 Feature: Folder project

  Scenario: Create folder
    When Go to NewItem
    And Type project name "test name Folder"
    And Choose project type as Folder
    And Click Ok and go to config
    And Save config and go to Folder project
    Then Folder project name is "test name Folder"
