package serenity.features.authentication2;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.ofss.serenity.moduleSteps.OBPModuleSteps;
import com.ofss.serenity.pages.BasePageofOrigination;
import com.ofss.serenity.requirements.Requirements;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Narrative;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.junit.annotations.UseTestDataFrom;

@Narrative(text={"1. In order to choose the best flight for my travels",
        "As a traveller",
        "I want to be able to search for flights between specific destinations"})
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value="D:\\eclipse-workspace\\ADFBDD\\Artifacts\\testData\\inputData.csv")
@Story(Requirements.Documents.Homeloan.class)
public class Origination2Test {

    private String applicationType,productType,productOffer;


    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setProductOffer(String productOffer) {
        this.productOffer = productOffer;
    }


    @Steps
    private OBPModuleSteps obpModuleSteps;
    @Steps
    private BasePageofOrigination basePageofOrigination ;

    @Managed
    public WebDriver userBrowser;

    @Test
    public void Submit_The_Application_Form2() {

        obpModuleSteps.Logs_Into_The_Application("OFSSUSER","welcome1","OR097");

        obpModuleSteps.Select_The_Products_For_Application(applicationType,productType,productOffer);
    
    }
    
    @Test
    public void Submit_The_Application_Form3() {

        obpModuleSteps.Logs_Into_The_Application("OFSSUSER","welcome1","OR097");

        obpModuleSteps.Select_The_Products_For_Application(applicationType,productType,productOffer);
    
    }


}
