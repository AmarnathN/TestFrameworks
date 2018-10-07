#Author: navuluri.amarnath@oracle.com

#Sample Feature Definition Template
Feature: Origination In Principle Application Form Submission for Dual Facility in OBP


Scenario: Single Applicant Dual Facility Account for abc
    Given test data sheet name "DocsOriginationTest" and test case ID "1"
    When User Logs in to Application Form
    And Selects the Products with required offers and Start Application

