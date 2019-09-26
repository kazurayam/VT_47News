import java.nio.file.Path

import org.openqa.selenium.WebDriver

import com.kazurayam.materials.MaterialRepository
import com.kazurayam.visualtesting.ManagedGlobalVariable as MGV
import com.kazurayam.visualtesting.HighlightElements
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.comment("*** GlobalVariable[${MGV.CURRENT_TESTSUITE_ID.getName()}]=${GlobalVariable[MGV.CURRENT_TESTSUITE_ID.getName()]}")
WebUI.comment("*** GlobalVariable[${MGV.CURRENT_TESTSUITE_TIMESTAMP.getName()}]=${GlobalVariable[MGV.CURRENT_TESTSUITE_TIMESTAMP.getName()]}")

MaterialRepository mr = (MaterialRepository)GlobalVariable[MGV.MATERIAL_REPOSITORY.getName()]
assert mr != null

URL url = new URL("https://www.47news.jp/")
HighlightElements lighter = new HighlightElements()

WebUI.openBrowser('')
WebUI.setViewPortSize(500, 800)
WebDriver driver = DriverFactory.getWebDriver()

WebUI.navigateToUrl(url.toExternalForm())

// 47news.jpのトップページのランキングの部分をハイライトする
TestObject toRanking = HighlightElements.createTestObjectWithXPath("ranking", 
	"//div[@id='two_line_ranking_html_4']/div/div[@class='ranking'][2]/ol/li[1]")
WebUI.verifyElementPresent(toRanking, 10, FailureHandling.STOP_ON_FAILURE)
lighter.highlightElement(toRanking)

Path png1 = mr.resolveScreenshotPathByURLPathComponents(
	GlobalVariable[MGV.CURRENT_TESTCASE_ID.getName()],
	url,
	0,
	'TOP')
CustomKeywords.'com.kazurayam.ksbackyard.ScreenshotDriver.takeEntirePage'(driver, png1.toFile(), 500)
WebUI.comment("saved image into ${png1}")

WebUI.closeBrowser()

Path highlight = png1.getParent().resolve('TOP.highlight.json')
lighter.writeLog(highlight)


