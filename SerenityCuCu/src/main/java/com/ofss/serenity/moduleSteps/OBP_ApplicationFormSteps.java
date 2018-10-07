package com.ofss.serenity.moduleSteps;

import org.jboss.logging.Logger;

import com.ofss.Utilities.frameworkUtilities.TestData;
import com.ofss.serenity.moduleActionSteps.LoginSteps;
import com.ofss.serenity.moduleActionSteps.ProductSelectionSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class OBP_ApplicationFormSteps {

	@Steps
	private LoginSteps loginSteps;
	@Steps
	private ProductSelectionSteps productSelectionSteps;
	
	private static final Logger LOGGER = Logger.getLogger(OBP_ApplicationFormSteps.class.getName());
	
	@Step
	public void Logs_Into_The_Application() {

		String userName = TestData.getInstance().getTestData("GV_OBP_USERNAME");
		String passWord = TestData.getInstance().getTestData("GV_OBP_PASSWORD");
		String FastPath = "OR097";

		loginSteps.isOnTheHomePage();

		loginSteps.logsIntoApplication(userName, passWord);

		loginSteps.shouldBeOnLandingPage();

		loginSteps.goToPageUsingFastPath(FastPath);

		loginSteps.logsIntoApplication(userName, passWord);
	}

	@Step
	public void Select_The_Products_For_Application() {

		String applicationType = TestData.getInstance().getTestData("TD_APPLICATIONTYPE");

		String firstProductType = TestData.getInstance()
				.getTestData("TD_FirstProductType_IfNotRequiredGiveNA".toUpperCase());

		String secondProductType = TestData.getInstance()
				.getTestData("TD_SecondProductType_IfNotRequiredGiveNA".toUpperCase());

		productSelectionSteps.selectTypeofApplication(applicationType);

		if (firstProductType != null) {
			if (!(firstProductType.equalsIgnoreCase("NA"))) {

				String firstProductOfferType = TestData.getInstance()
						.getTestData("TD_FitstProductOfferType".toUpperCase());

				productSelectionSteps.selectTypeofProduct(firstProductType);

				switch (firstProductType.toUpperCase()) {
				case "BUNDLE":
					String firstProduct_Bundle_FirstOptionalOffer = TestData.getInstance()
							.getTestData("TD_FirstProduct_Bundle_FirstOptionalOffer");

					productSelectionSteps.addBundleProductOfferToCart(firstProductOfferType,
							firstProduct_Bundle_FirstOptionalOffer);
					break;

				case "LENDING":

					productSelectionSteps.addProductOfferToCart(firstProductOfferType);
					break;

				case "DEPOSIT":

					productSelectionSteps.addProductOfferToCart(firstProductOfferType);
					break;

				default:
					LOGGER.error("Inavlid First Product Type " + firstProductType +"Provided ");
				}

			}
		}

		if (secondProductType != null) {
			if (!(secondProductType.equalsIgnoreCase("NA"))) {
				String secondProductOfferType = TestData.getInstance()
						.getTestData("TD_SecondProductOfferType".toUpperCase());

				productSelectionSteps.selectTypeofProduct(secondProductType);

				productSelectionSteps.addProductOfferToCart(secondProductOfferType);

			}
		}

		productSelectionSteps.startApplication();

	}
}
