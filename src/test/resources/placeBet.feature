Feature: Placing bet

    Scenario: Placing bet on Premier League match upon login on HomePage
    Given The William Hill homepage
    And User login sucessfully
    And User navigate to Premiership football event
    When User place 16.9 GBP bet on Home team
    Then Odds and returns should be offered


