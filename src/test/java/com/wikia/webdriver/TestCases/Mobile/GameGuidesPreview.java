package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileModalComponentObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author PMG
 *
 * Below test cases are executed against Special:GameGuidesPreview
 * 1. Verify that sections are opened in article view
 * 2. Verify that hide button in section works
 * 3. Verify that next image button in modal works
 * 4. Verify that previous image button in modal works
 * 5. Verify that you are able to hide top bar in modal
 * 6. Verify that back button close modal
 * 7. Verify that when you go to modal and go back you are in the same place as previously.
 */
public class GameGuidesPreview extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups={"GameGuidesPreview_001", "SectionsTests", "MobileGG"})
	public void GameGuidesPreview_001_sections_chevronTest() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		driver.get(wikiURL + URLsContent.wikiaPhp);
		article.appendToUrl(URLsContent.gameGuidesControllerQS);
		article.appendToUrl(URLsContent.renderFullQS);
		article.appendToUrl(URLsContent.pageName + "Sections");
		article.clickSection(1);
		article.verifySectionVisibility();
		article.clickSection(1);
		article.verifySectionInvisibility();
	}

	@Test(groups={"GameGuidesPreview_002", "SectionsTests", "MobileGG"})
	public void GameGuidesPreview_002_sections_hideTest() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		driver.get(wikiURL + URLsContent.wikiaPhp);
		article.appendToUrl(URLsContent.gameGuidesControllerQS);
		article.appendToUrl(URLsContent.renderFullQS);
		article.appendToUrl(URLsContent.pageName + "Sections");
		article.clickSection(1);
		article.verifySectionVisibility();
		article.clickHideButton();
		article.verifySectionInvisibility();
	}

	@Test(groups={"GameGuidesPreview_003", "ModalTests", "MobileGG"})
	public void GameGuidesPreview_003_modalTest_nextImage() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		driver.get(wikiURL + URLsContent.wikiaPhp);
		mobile.appendToUrl(URLsContent.gameGuidesControllerQS);
		mobile.appendToUrl(URLsContent.renderFullQS);
		mobile.appendToUrl(URLsContent.pageName + "Modal");
		MobileModalComponentObject modal = mobile.clickModal();
		String current = modal.getCurrentImageUrl();
		modal.goToNextImage();
		Assertion.assertNotEquals(current, modal.getCurrentImageUrl());
		modal.closeModal();
		modal.verifyModalClosed();
	}

	@Test(groups={"GameGuidesPreview_004", "ModalTests", "MobileGG"})
	public void GameGuidesPreview_004_modalTest_previousImage() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		driver.get(wikiURL + URLsContent.wikiaPhp);
		mobile.appendToUrl(URLsContent.gameGuidesControllerQS);
		mobile.appendToUrl(URLsContent.renderFullQS);
		mobile.appendToUrl(URLsContent.pageName + "Modal");
		MobileModalComponentObject modal = mobile.clickModal();
		String current = modal.getCurrentImageUrl();
		modal.goToPreviousImage();
		Assertion.assertNotEquals(current, modal.getCurrentImageUrl());
		modal.closeModal();
		modal.verifyModalClosed();
	}

	@Test(groups={"GameGuidesPreview_005", "ModalTests", "MobileGG"})
	public void GameGuidesPreview_005_topBarVisibleOrNot() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		driver.get(wikiURL + URLsContent.wikiaPhp);
		mobile.appendToUrl(URLsContent.gameGuidesControllerQS);
		mobile.appendToUrl(URLsContent.renderFullQS);
		mobile.appendToUrl(URLsContent.pageName + "Modal");
		MobileModalComponentObject modal = mobile.clickModal();
		modal.verifyTopBarVisible();
		modal.hideTopBar();
		modal.verifyTopBarHidden();
		modal.showTopBar();
		modal.verifyTopBarVisible();
	}

	@Test(groups={"GameGuidesPreview_006", "ModalTests", "MobileGG"})
	public void GameGuidesPreview_006_backButton() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		driver.get(wikiURL + URLsContent.wikiaPhp);
		mobile.appendToUrl(URLsContent.gameGuidesControllerQS);
		mobile.appendToUrl(URLsContent.renderFullQS);
		mobile.appendToUrl(URLsContent.pageName + "Modal");
		MobileModalComponentObject modal = mobile.clickOpenedImage(5);
		modal.closeModalWithBackButton();
		modal.verifyModalClosed();
	}

	@Test(groups={"GameGuidesPreview_007", "ModalTests", "MobileGG"})
	public void GameGuidesPreview_007_positionAfterCloseModal() {
		MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
		driver.get(wikiURL + URLsContent.wikiaPhp);
		mobile.appendToUrl(URLsContent.gameGuidesControllerQS);
		mobile.appendToUrl(URLsContent.renderFullQS);
		mobile.appendToUrl(URLsContent.pageName + "Modal");
		MobileModalComponentObject modal = mobile.clickOpenedImage(5);
		modal.closeModal();
		modal.verifyModalClosed();
		Long positionBeforeModal = modal.getPosition();
		mobile.clickOpenedImage();
		modal.closeModal();
		modal.verifyModalClosed();
		modal.verifyPositionTheSame(positionBeforeModal);
	}
}