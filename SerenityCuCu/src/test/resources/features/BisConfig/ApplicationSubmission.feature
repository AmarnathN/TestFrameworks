#Author: navuluri.amarnath@oracle.com

#Sample Feature Definition Template
Feature: Origination Application Form Submission for Home Loanin OBP

Scenario Outline: Single Applicant Home Loan for abc
    Given test data sheet name "OriginationTest" and test case ID "<TestCaseId>"
    When User Logs in to Application Form
    And Selects the Products with required offers and Start Application
    And Add the Party Details of the First Product
    

Examples:
    |TestCaseId | 
    |TC01|
    |TC02| 
 