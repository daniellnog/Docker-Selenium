package TestRunner;		

import org.junit.runner.RunWith;		
import cucumber.api.CucumberOptions;		
import cucumber.api.junit.Cucumber;		

@RunWith(Cucumber.class)				
@CucumberOptions(features="src/test/java/Features/Sortable_Challenge.feature",glue={"StepDefinition"})

public class Runner {

}