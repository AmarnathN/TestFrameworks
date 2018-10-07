$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("myFeature1.feature");
formatter.feature({
  "line": 1,
  "name": "Origination Home Loan",
  "description": "",
  "id": "origination-home-loan",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "comments": [
    {
      "line": 3,
      "value": "#Scenario Outline: Single Applicant Home Loan for acbc1"
    },
    {
      "line": 4,
      "value": "#    Given test data \"TD_MyFeature1\""
    },
    {
      "line": 5,
      "value": "#    Then User Login to Application Form screen \"FastPath\" with username \"GV_OBP_Username\" and Password \"GV_OBP_Password\""
    },
    {
      "line": 6,
      "value": "#    Then Select \"ApplicationType\" of \"ProductType\" Product with offer \"ProductOffer\" and Start Application"
    },
    {
      "line": 7,
      "value": "#  Then search party  based on \"\u003cFirstApplicantBasedOn\u003e\" search by \"\u003cFirstApplicantPartyType\u003e\" with search details :PartyId \"\u003cFirstApplicantPartyId\u003e\",First Name \"\u003cFirstApplicantFirstName\u003e\",Last Name \"\u003cFirstApplicantLastName\u003e\",Email Id \"\u003cFirstApplicantEmailId\u003e\" and add as \"\u003cFirstApplicantType\u003e\""
    },
    {
      "line": 8,
      "value": "#  Then search party  based on \"\u003cFirstApplicantBasedOn\u003e\" search by \"\u003cFirstApplicantPartyType\u003e\" with search details :PartyId \"\u003cFirstApplicantPartyId\u003e\",First Name \"\u003cFirstApplicantFirstName\u003e\",Last Name \"\u003cFirstApplicantLastName\u003e\",Email Id \"\u003cFirstApplicantEmailId\u003e\" and add as \"\u003cFirstApplicantType\u003e\""
    },
    {
      "line": 11,
      "value": "#Examples:"
    },
    {
      "line": 12,
      "value": "#    |ApplicationType | ProductType | ProductOffer        |FirstApplicantType |FirstApplicantBasedOn|FirstApplicantPartyType|FirstApplicantPartyId|FirstApplicantFirstName|FirstApplicantLastName|FirstApplicantEmailId|"
    },
    {
      "line": 13,
      "value": "#    |Application     | Lending     | Westpac Home Loan   |Primary Applicant  |Party type           |Individual             |                     |AB5979                 |Dsouza                |                     |"
    }
  ],
  "line": 15,
  "name": "Single Applicant Home Loan for acbc1",
  "description": "",
  "id": "origination-home-loan;single-applicant-home-loan-for-acbc1",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 16,
  "name": "test data sheet name \"OriginationTest\" and test case ID \"\u003cTestCaseId\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "User Login to Application Form screen with username \"GV_OBP_Username\" and Password \"GV_OBP_Password\"",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "Select \"TD_ApplicationType\" of \"TD_ProductType\" Product with offer \"TD_ProductOffer\" and Start Application",
  "keyword": "When "
});
formatter.examples({
  "line": 21,
  "name": "",
  "description": "",
  "id": "origination-home-loan;single-applicant-home-loan-for-acbc1;",
  "rows": [
    {
      "cells": [
        "TestCaseId"
      ],
      "line": 22,
      "id": "origination-home-loan;single-applicant-home-loan-for-acbc1;;1"
    },
    {
      "cells": [
        "1"
      ],
      "line": 23,
      "id": "origination-home-loan;single-applicant-home-loan-for-acbc1;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 281014859,
  "status": "passed"
});
formatter.scenario({
  "line": 23,
  "name": "Single Applicant Home Loan for acbc1",
  "description": "",
  "id": "origination-home-loan;single-applicant-home-loan-for-acbc1;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 16,
  "name": "test data sheet name \"OriginationTest\" and test case ID \"1\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "User Login to Application Form screen with username \"GV_OBP_Username\" and Password \"GV_OBP_Password\"",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "Select \"TD_ApplicationType\" of \"TD_ProductType\" Product with offer \"TD_ProductOffer\" and Start Application",
  "keyword": "When "
});
formatter.match({
  "arguments": [
    {
      "val": "OriginationTest",
      "offset": 22
    },
    {
      "val": "1",
      "offset": 57
    }
  ],
  "location": "stepDefinitions.test_data_sheet_name_and_test_case_ID(String,String)"
});
formatter.result({
  "duration": 6608700468,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "GV_OBP_Username",
      "offset": 53
    },
    {
      "val": "GV_OBP_Password",
      "offset": 84
    }
  ],
  "location": "stepDefinitions.user_Login_to_Application_Form_screen_with_username_and_Password(String,String)"
});
formatter.result({
  "duration": 27912543626,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "TD_ApplicationType",
      "offset": 8
    },
    {
      "val": "TD_ProductType",
      "offset": 32
    },
    {
      "val": "TD_ProductOffer",
      "offset": 68
    }
  ],
  "location": "stepDefinitions.select_of_Product_with_offer_and_Start_Application(String,String,String)"
});
formatter.result({
  "duration": 43439531307,
  "status": "passed"
});
formatter.after({
  "duration": 131692,
  "status": "passed"
});
});