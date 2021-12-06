package com.jsonPlaceholder.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = "html:target/cucumber-report.html",
            features = "src/test/resources/features", //path to feature folder
            glue = "com/jsonPlaceholder/step_definitions", //path to CukesRunner class
            dryRun = false,
            tags = "@Users"
    )

    public class CukesRunner {
        //it is a start button, it's starting trigger for the whole project
    }

