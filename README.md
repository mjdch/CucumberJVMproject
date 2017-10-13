# Cucumber JVM project

Repository created for Interview Test purpose.

# Execution
### Precodnitions
Test framework was tested and works on Windows and Linux 64 bit environment (chromedriver itself is provided in repository). Also these preconditions:
  - **JDK** installed and configured 
  - **Google Chrome** The newest version installed on system in default directory
  - **Maven** added to PATH system variable (or use it from directory)
  - File *account.properties* in repository is filled-up with proper username & password to WH Website (I resigned to publish my test account in public repository)
### Test execution 
Test execution using Maven or from IDE (i.e IntelliJ Idea). We can run test in two ways. By default test is run on Google Chrome with maximized window. We launch test by:
```sh
mvn clean test
```
We also could launch Google Chrome with Mobile Device Emulation to do that we launch test with:
```sh
mvn -Ddevice="<DEVICE_NAME>" clean test
```
Where *DEVICE_NAME* is name of Devices avaible in Google Chrome Developers Tools for example "Nexus 5":
```sh
mvn -Ddevice="Nexus 5" clean test
```
\* The same result we can achieve by passing program argument in IDE.
### Reports

Simple test report created using build-in Cucumber library *info.cukes:cucumber-html* set up by
```java
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-html-report"},
        features = "src/test/resources"
)
```
### Comments
All my implementing choices and comments are included in sourcecode.
