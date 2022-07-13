Feature: Freestyle project

  Scenario: Create project
    When Go to NewItem
    And Type project name "test name"
    And Choose project type as Freestyle
    And Click Ok and go to config
    And Save config and go to Freestyle project
    Then Freestyle project name is "test name"

  Scenario: Edit project
    When Click Freestyle project "test name"
    And Click Freestyle configure
    And Type Freestyle project description as "test description"
    And Save config and go to Freestyle project
    Then Project description is "test description"

  Scenario Outline: Add new project
    When Go to NewItem
    And Type project name "<project_name>"
    And Choose project type as "<project_type>"
    And Click Ok and go to config
    And Go home
    Then Project with name "<project_name>" is exists

  Examples:
    | project_name     | project_type |
    | Freestyle name   | Freestyle    |
    | Folder name      | Folder       |
