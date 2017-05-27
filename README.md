# Amazon Cucumber Example Documentation
By: Kevin Nam (kevin.nam@mail.mcgill.ca)
For: Robert Sabourin

## Requirements
1. JDK v1.8 [Link](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Maven v3.3+ [Link](https://maven.apache.org/download.cgi)
3. IntelliJ 2017.1.3 Community Edition, with plugins:
   - Gherkin
   - Cucumber-Java
4. Google Chrome

## Dependencies Used

Using Maven, all dependencies can be easily acquired and used. Refer to */pom.xml* for more details.

```
<dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.4.0</version>
        </dependency>
        <dependency>
            <groupId>info.cukes</groupId>
            <artifactId>cucumber-java</artifactId>
            <scope>test</scope>
            <version>1.2.5</version>
        </dependency>
        <dependency>
            <groupId>info.cukes</groupId>
            <artifactId>cucumber-jvm</artifactId>
            <version>1.2.5</version>
            <type>pom</type>
        </dependency>
</dependencies>
```

## Classes Explained

All java classes can be found under *src/test/java/com/robsab/example/*.

1. **AmazonStepDefinitions**
  * Contains all the logic for *Given, When, Then* functionality to be used in the feature files.
  * It is important to modify the iAmUsingGoogleChromeBrowser method to use the correct chrome driver depending on the OS.
2. **StaticVariables**
  * Contains static variables found on the Amazon web page pertaining to ids and classes of elements on its pages.
  * Not necessary to modify unless Amazon changes the ids or classes of relevant elements.

## Feature files included

Two feature files can be found under *src/resources/features/*.

1. **user_adds_items_to_cart.feature** contains three successful scenarios:
   1. User adds a known item to his/her cart
   2. User searches for an item and adds it to his/her cart
   3. User searches for an item with size and adds it to his/her cart

2. **user_adds_items_to_cart_failing_case.feature** contains one failing case scenario:
   1. User searches for a *(nonexistant)* item and adds it to his/her cart

## How to run feature files for testing

Feature files must be *glued* to the package where the step definitions lie.
You can right-click on any feature file and run it, but they must be configured as follows:

![example](http://i.imgur.com/K0HXlMt.png "Example configuration")


