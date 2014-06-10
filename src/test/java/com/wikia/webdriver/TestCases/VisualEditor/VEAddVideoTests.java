package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * VE-1134 Adding Youtube Video
 * VE-1134 Adding Premium Video with full URL
 * VE-1134 Adding Premium Video with file URI
 *
 */

public class VEAddVideoTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(
		groups = {"VEAddVideo", "VEAddExternalVideoTests_001", "VEAddExternalVideo"}
	)
	public void VEEnabledEditorEntryVEPreferredTests_001_AddNonPremiumVid() throws InterruptedException {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.selectInsertToOpenDialog(InsertDialog.MEDIA);
		VisualEditorPageObject veNew = mediaDialog.addMedia(VideoContent.nonPremiumVideoURL);
		veNew.verifyVideo();
		veNew.verifyVEToolBarPresent();
		VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
		article = save.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}

	@Test(
		groups = {"VEAddVideo", "VEAddExternalVideoTests_002", "VEAddExternalVideo"}
	)
	public void VEEnabledEditorEntryVEPreferredTests_002_AddPremiumVid() {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.selectInsertToOpenDialog(InsertDialog.MEDIA);
		VisualEditorPageObject veNew = mediaDialog.addMedia(VideoContent.premiumVideoURL);
		veNew.verifyVideo();
		veNew.verifyVEToolBarPresent();
		VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
		article = save.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}
}