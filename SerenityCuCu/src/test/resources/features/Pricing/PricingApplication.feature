#Author: navuluri.amarnath@oracle.com

#Sample Feature Definition Template
Feature: Origination In Principle Application Form Submission for CASA Account in OBP


Scenario: Single Applicant Casa Account for abc
    Given test data sheet name "PricingOriginationTest" and test case ID "2"
    When User Logs in to Application Form
    And Selects the Products with required offers and Start Application

