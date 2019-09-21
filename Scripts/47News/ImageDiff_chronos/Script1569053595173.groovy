import com.kazurayam.visualtesting.ImageCollectionDifferDriver
import com.kazurayam.visualtesting.ImageCollectionDifferDriver.ChronosOptions

/*
 * Test Cases/47News/ImageDiff_chronos
 */
String TESTSUITE_ID = '47News/chronos_capture'
ChronosOptions options = new ChronosOptions.Builder().
							filterDataLessThan(0.0).
							shiftCriteriaPercentageBy(0.0).
							build()

CustomKeywords.'com.kazurayam.visualtesting.ImageDiffer.runChronos'(TESTSUITE_ID, options)

