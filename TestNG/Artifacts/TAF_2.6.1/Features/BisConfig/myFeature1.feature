Feature: Origination Home Loan

#Scenario Outline: Single Applicant Home Loan for acbc1
#    Given test data "TD_MyFeature1"
#    Then User Login to Application Form screen "FastPath" with username "GV_OBP_Username" and Password "GV_OBP_Password" 
#    Then Select "ApplicationType" of "ProductType" Product with offer "ProductOffer" and Start Application
  #  Then search party  based on "<FirstApplicantBasedOn>" search by "<FirstApplicantPartyType>" with search details :PartyId "<FirstApplicantPartyId>",First Name "<FirstApplicantFirstName>",Last Name "<FirstApplicantLastName>",Email Id "<FirstApplicantEmailId>" and add as "<FirstApplicantType>"
  #  Then search party  based on "<FirstApplicantBasedOn>" search by "<FirstApplicantPartyType>" with search details :PartyId "<FirstApplicantPartyId>",First Name "<FirstApplicantFirstName>",Last Name "<FirstApplicantLastName>",Email Id "<FirstApplicantEmailId>" and add as "<FirstApplicantType>"


#Examples:
#    |ApplicationType | ProductType | ProductOffer        |FirstApplicantType |FirstApplicantBasedOn|FirstApplicantPartyType|FirstApplicantPartyId|FirstApplicantFirstName|FirstApplicantLastName|FirstApplicantEmailId|
#    |Application     | Lending     | Westpac Home Loan   |Primary Applicant  |Party type           |Individual             |                     |AB5979                 |Dsouza                |                     |

Scenario Outline: Single Applicant Home Loan for acbc1
    Given test data sheet name "OriginationTest" and test case ID "<TestCaseId>"
    When User Login to Application Form screen with username "GV_OBP_Username" and Password "GV_OBP_Password" 
    When Select "TD_ApplicationType" of "TD_ProductType" Product with offer "TD_ProductOffer" and Start Application
  

Examples:
    |TestCaseId | 
    |1| 

