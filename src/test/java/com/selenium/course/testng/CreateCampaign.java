package com.selenium.course.testng;

import com.selenium.course.framework.WebDriverManager;
import com.selenium.course.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created for Joel Rodriguez
 */

public class CreateCampaign {

	private CampaignDetailsForm campaignDetailsForm;

	String campaignName = "CampaignTesting123";

	@BeforeClass
	public void setUp() {
		LoginPage loginPage = new LoginPage();
		ContentPage contentPage = loginPage.loginAsPrimaryUser();
		TabBar tabBar = contentPage.goToTabBar();
		TabPage campaignsPage = tabBar.clickCampaigns();
		campaignsPage.clickNewBtn();
	}

	@Test(groups = {"BVT, Acceptance, Funcional"})
	public void testCreateNewCampaign() {
		WebDriver driver = WebDriverManager.getInstance().getDriver();

		campaignDetailsForm = new CampaignsMainForm(driver)
				.setCampaignName(campaignName)
				.clickSaveCampaign();

		assertTrue(campaignDetailsForm.verifyNewCampaign(campaignName));
	}

	@AfterClass
	public void tearDown() {
		campaignDetailsForm.deleteCampaign();
	}
}