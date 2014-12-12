package com.wikia.webdriver.testcases.specialpagestests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.licensedvideoswap.LicensedVideoSwapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.licensedvideoswap.LicensedVideoSwapHistoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * Created by kenkouot on 3/20/14.
 */
public class LicensedVideoSwap extends NewTestTemplate {

	LicensedVideoSwapPageObject licensedVideoSwap;

	Credentials credentials = config.getCredentials();

	@BeforeMethod(alwaysRun = true)
	public void lvsSetup() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		licensedVideoSwap = wiki.openLicensedVideoSwap(wikiURL);
	}

	@Test(groups =  {"LicensedVideoSwap_001", "LicensedVideoSwap", "Media"})
	public void LicensedVideoSwap_001_navigateToHistory() {
		LicensedVideoSwapHistoryPageObject historyPage = licensedVideoSwap.navigateToHistoryPage();
		historyPage.verifyOnHistoryPage();
	}

	@Test(groups =  {"LicensedVideoSwap_002", "LicensedVideoSwap", "Media"})
	public void LicensedVideoSwap_002_navigateToHistoryAndBackToLvs() {
		LicensedVideoSwapHistoryPageObject historyPage = licensedVideoSwap.navigateToHistoryPage();
		historyPage.verifyOnHistoryPage();
		historyPage.navigateToLvsPage();
		licensedVideoSwap.verifyOnLvsPage();
	}

	@Test(groups =  {"LicensedVideoSwap_003", "LicensedVideoSwap", "Media"})
	public void LicensedVideoSwap_003_swap() {
		licensedVideoSwap.verifySwapVideo();

		LicensedVideoSwapHistoryPageObject historyPage = licensedVideoSwap.navigateToHistoryPage();
		historyPage.verifyOnHistoryPage();
		historyPage.clickUndoSwapLink();
		historyPage.verifyUndoSucceeded();
	}
}