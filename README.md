# UI Testing with Selenium Java , Cucumber & Junit - Page Factory

## Introduction

The `Cucumber Selenium Java UI tests Framework` is testing miro project end to end UI with Selenium WebDriver, written in Java with JUnit4, using the Page Factory design pattern and driven via Cucumber BDD feature files. It can be used to kickstart testing of other UIs with minimal changes to the project. We have also used ashot to test the image comparison test

##Software Prerequisite
- JDK 8 recommended or above(Version supports lambda, annotation etc.)
- Maven - apache-maven-3.8.1
- IntelliJ idea latest version recommended or any idea supports maven, cucumber-java, gherkins.

After installation of software make sure all system path has been set correctly.
- to check java  - open terminal and check this command returning the version `java --version`
- to check maven  - open terminal and check this command returning the version `mvn -v`
- check all cucumber-java, gherkins plugins are installed(please check compatible version with your editor)

## Installation
Once you have all above prerequisite, clone the project in your preferred location. Then import the project in your editor as maven project and run below command.
`mvn clean test -DskipTests`

## Command line

The validation can be triggered by executing the following command in the target project's main directory:
```
mvn clean test -Dprofile=e1 -Dcucumber.options="--tags @allTests"
```

`-Dprofile` is to set the environment variable which will help to load required config to execute tests in that environment. It is defaulted to `e1` if that is not passed.

`Dcucumber.options="--tags @XXX"` this is being used to run specific tests. there are few available tags that will allow you to run specific set of test with required test data.
Available tags are ``@allTests, @miroUserValidation``
- `@allTests` is to execute available all tests


##Execution using Editor
Run `CucumberTests` file as JUnit. It will trigger test case execution based on tags mentioned in that class. If you want to run against different environment and different tags without changing anything in CucumberTests class, then just pass `-Dprofile=e1 -Dcucumber.options="--tags @allTests"` as jvm argument and modify these value as needed.
The properties file test-config can be used for configuration of different environment values.

## Reporting
Execution reports will be avaiable in `build\cucumber-html-reports` folder as `overview-features.html` which you can open in any browser(chrome recommended).
This report will hold all the record that you need to analyze in case you need to. It will provide in details execution report.


### Tech stack
As this is a Java project, build and dependency management is handled by Maven, so there is a `pom.xml` file defining the versions of the dependencies:
* Java v11
* Selenium v3.141.59
* JUnit v4.13.2
* Cucumber v6.10.4
* WebDriverManager v4.4.3
* ashot v1.5.4

The Cucumber version is the latest version at the time of writing (released May 2021).

WebDriverManager is a third-party extension for Selenium that makes it easier to manage the drivers for multiple browsers, making cross-browser testing simpler.


### Project Structure
The project uses a standard structure and naming convention, incorporating the URL of the website under test, i.e. the test code is all stored under `src/test/java/com/the_internet/herokuapp`. Below that we have:
* `features`  - this folder contains the Cucumber `.feature` files, one per website page. By separating out the tests for each page into separate feature files we continue the Page Factory theme of page independence and make it easier to extend the framework in the future. Each feature file is named after the page it tests, e.g. DropdownPage.feature contains the tests for the Dropdown page.
* `pages` - the Page Factory implementation of the individual website pages, one class file per page. Each class is named after the corresponding page e.g. `HomePage.java`, `CheckboxesPage.java` etc. There is also an abstract `BasePage.java` which the other pages implement.
* `steps` - a collection of classes containing the implementation of the steps from the Cucumber feature files. As above, there is one steps class per page and each is named after the page under test, e.g. `FormAuthenticationPageSteps.java`. There is an additional `CommonSteps.java` class containing several steps that are, as the name suggests, used by more than one feature file. This avoids the need to duplicate code across individual steps classes.
* `DriverManager.java` - set up the driver, based on the selected browser, using the [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) extension.
* `TestRunner.java` - the main JUnit test runner class, decorated with the annotation required to run Cucumber tests. The class itself is empty but the `CucumberOptions` annotation defines the location of the features and associated steps.

### Page Factory Classes
As noted above, the `pages` folder contains the relevant Page Factory classes for each tested page. Each page class, including the abstract `BasePage` class, follows the same pattern:
* `WebElement` variables decorated with the `@FindBy` annotation parameterised by selector,
* a class constructor to initialise the above Page Factory elements
* public interaction methods e.g. to click on an element, get an element’s text etc. using the above `WebElement` variables directly

This way we encapsulate the web elements themselves, only allowing the interactions that have been implemented via public methods, ensuring the tests (in effect, the user) can only interact with the web page in known ways.


### Supported Browsers
The `DriverManager` class uses the WebDriverManager dependency to manage the various browser drivers. The `getDriver` method returns the relevant WebDriver instance for the chosen browser, with support for:
* Chrome - the default option
* Firefox
* Edge
* Safari

`DriverManager` also sets the position and size of the browser window, ensuring consistency between tests and on different environments, and the implicit wait time i.e. the time Selenium will wait for an element to be displayed.