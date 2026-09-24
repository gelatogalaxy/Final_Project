package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        glue = {"APIStepDefs"},
        features = {"src/test/resources/APITest.feature"},
        plugin = {"pretty", "html:reports/api-cucumber.html", "json:reports/api-cucumber.json"})
public class APICucumberTest extends AbstractTestNGCucumberTests {

    // Gradle 9 tidak mendeteksi @Test yang hanya diwarisi dari AbstractTestNGCucumberTests,
    // akibatnya "gradle test" gagal dengan "did not discover any tests".
    // Override di bawah ini membuat anotasi TestNG ada langsung di class runner ini.
    @Override
    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        super.runScenario(pickleWrapper, featureWrapper);
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
