package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'rochan' Chan
 */
public class VisualEditorReviewChangesDialog extends WikiBasePageObject {

	@FindBy(css=".oo-ui-dialog-open .oo-ui-frame")
	private WebElement reviewDialogIFrame;
	@FindBy(css=".secondary .oo-ui-labeledElement-label")
	private WebElement reviewChangesButton;
	@FindBy(css=
		".oo-ui-window-foot " +
		"div:not(.oo-ui-flaggableElement-secondary):not(.oo-ui-flaggableElement-constructive) " +
		".oo-ui-labeledElement-label")
	private WebElement returnToSaveFormButton;
	@FindBy(css=".oo-ui-window-head .oo-ui-icon-close")
	private WebElement closeButton;
	@FindBy(css=".ve-ui-mwSaveDialog-viewer.WikiaArticle")
	private WebElement wikiaArticle;
	@FindBy(css=".ve-ui-mwSaveDialog-viewer.WikiaArticle pre")
	private WebElement wikiaAritlceFirstPreview;
	@FindBy(css=".ve-ui-mwSaveDialog-viewer.WikiaArticle")
	private WebElement wikiaArticleDiffTable;

	public VisualEditorReviewChangesDialog(WebDriver driver) {
		super(driver);
	}

	public VisualEditorSaveChangesDialog clickReturnToSaveFormButton() {
		waitForElementVisibleByElement(reviewDialogIFrame);
		driver.switchTo().frame(reviewDialogIFrame);
		waitForElementClickableByElement(returnToSaveFormButton);
		returnToSaveFormButton.click();
		PageObjectLogging.log("clickReturnToSaveFormButton", "Return To Save Form button clicked", true);
		waitForElementNotVisibleByElement(reviewDialogIFrame);
		driver.switchTo().defaultContent();
		return new VisualEditorSaveChangesDialog(driver);
	}

	public VisualEditorPageObject clickCloseButton() {
		waitForElementVisibleByElement(reviewDialogIFrame);
		driver.switchTo().frame(reviewDialogIFrame);
		waitForElementClickableByElement(closeButton);
		closeButton.click();
		PageObjectLogging.log("clickCloseButton", "Close button clicked", true);
		waitForElementNotVisibleByElement(reviewDialogIFrame);
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}

	public void verifyAddedDiffs(ArrayList<String> targets) {
		waitForElementVisibleByElement(reviewDialogIFrame);
		driver.switchTo().frame(reviewDialogIFrame);
		if (wikiaAritlceFirstPreview != null) {
			verifyNewArticleDiffs(targets);
		} else {
			verifyModifiedArticleDiffs(targets);
		}
		waitForElementNotVisibleByElement(reviewDialogIFrame);
		driver.switchTo().defaultContent();
	}

	private void verifyModifiedArticleDiffs(ArrayList<String> targets) {
		// TODO Auto-generated method stub
	}

	private void verifyNewArticleDiffs(ArrayList<String> targets) {
		String wikiText = wikiaAritlceFirstPreview.getText();
		for (String target : targets) {
			Assertion.assertStringContains(wikiText, target);
		}
	}

	private void verifyAddedDiff(String target) {

	}
}
