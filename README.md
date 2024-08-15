List of Annotations
===================
1. @Component
2. @ComponentScan
3. @Service
4. @Repository
5. @Controller
6. @Bean
7. @Qualifier
8. @Primary
9. @Lazy
10. @Scope
11. @PostConstruct
12. @PreDestroy
13. @Autowired
14. @Value
15. @Configuration
16. @Import
17. @ImportResource
18. @PropertySource
There are 2 different ways to perform injection to spring bean properties
a. @Value => It can be used to inject each value to spring bean properties
b. @ConfigurationProperties => It can be used to perform bulk injection.
eg:
input.properties
======================
org.info.companyName = ineuron
org.info.companyLoc = bengaluru
org.info.companyType = IT
using @Value
============
@Component("company)
@PropertySource(location = "in/commons/properties/input.properties")
public class Company{
@Value("${org.info.companyName}")
private String name;
@Value("${org.info.companyLoc}")
private String adress;
@Value("${org.info.copmanyType}")
private String type;
}
using @ConfigurationProperties
==============================
@Component("company)

@PropertySource("application.properties")
@ConfigurationProperites(prefix= "org.info")
public class Company{
private String companyName;
private String companyLoc;
private String companyType;
setXXXX(),toString()
}
What is the difference b/w @Value and @CofigurationProperties?
@Value
=> It is given by Spring framework,so it can be used in Spring and SpringBoot
applications.
=> Support single value injection to Spring bean property.
=> It performs field level injection(setters not required)
=> Common prefix of all keys are not required in
application.properties/application.yml file
=> Keys in properties file and property names need not match.
=> If specified key is not present then it would result in
"IllegalArgumentException".
@ConfigurationProperties
=> It is given by SpringBoot framework,so it can be used only SpringBoot
applications.
=> Support bulk operation
=> It perform setter level injection internally, so setters are mandatory
=> Common prefix of all keys are required in
application.properties/application.yml file.
=> keys in properties file and property names should match
=> If the matching key is not found then it would neglect the injection.
Note: While working with @ConfigurationProperties, it is always suggested to add
configuarationProcessor inside pom.xml file
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-configuration-processor</artifactId>
<optional>true</optional>
</dependency>
Note:
If we try to inject different values to spring bean property using both
Fieldlevel(@value) and @ConfigurationProperties annotations, which one will be
injection?
Answer:: Since @ConfigurationProperties uses setter injection,so the values
injected at field level(@Value) will be overriden with

Setter level
application.properties
======================
my.app.name=ineuron
my.app.location=bengaluru
my.app.type=IT
org.ineuron.name=PhysicsWallah
org.ineuron.location=Delhi
org.ineuron.type=EdTech

@Component(value="comp1")
@ConfigurationProperties(prefix = "org.ineuron")
public class Company1 {
private String name;
private String type;
private String location;
setter/toString()

}
@Component(value = "comp2")
@ConfigurationProperties(prefix = "my.app")
public class Company2 {
private String name;
private String type;
private String location;
setter/toString()
}
output
Company [name=PhysicsWallah, type=EdTech, location=Delhi]
Company [name=ineuron, type=IT, location=bengaluru]
Injecting values to different types like Arrays,List,Set,Map,HAS-A Property of
SpringBean using Properties/.yml file
===================================================================================
=================================
=> The allowed special characters in properties file is ".","-","[]".
=> To work with Array,List,Set we need to use
prefix.<propertyName>[index]=value //index should be sequential
=> To work with Map<K,V> we need to use prefix.<propertyName>.<key>=<value>.

refer::BootProj04-

BeanInjectionWithCollectionProperties
application.properties
======================
emp.info.id=10
emp.info.name=sachin
#HAS-A property injection
emp.info.company.name=MI
emp.info.company.location=Bandra
emp.info.company.size=35
#Array object injection
emp.info.skill-set[0]=java
emp.info.skill-set[1]=jee
emp.info.skill-set[2]=ORM
emp.info.skill-set[3]=SpringBoot
#List object injection
emp.info.project-names[0]=IND
emp.info.project-names[1]=World1X
emp.info.project-names[2]=Mumbai
emp.info.project-names[3]=Asia1X

#Set Object injection
emp.info.mobile-numbers[0]=9997778886
emp.info.mobile-numbers[1]=6667778889
emp.info.mobile-numbers[2]=5556667776
emp.info.mobile-numbers[3]=5556667776
#Map object injection
emp.info.id-details.adharNo=7645345
emp.info.id-details.panNo=232345
emp.info.id-details.voterId=2323454
@Component(value = "employee")
@ConfigurationProperties(prefix = "emp.info")
public class Employee {
private String name;
private long id;
private Company company;
private String[] skillSet;
private List<String> projectNames;
private Set<Long> mobileNumbers;
private Map<String, Object> idDetails;
}
@Component("company")
public class Company {
private String name;
private String location;
private int size;
}
Output
======
Employee[ name=sachin, id=10,

company=Company [name=MI, location=Bandra, size=35],
skillSet=[java, jee, ORM, SpringBoot],
projectNames=[IND, World1X, Mumbai, Asia1X],
mobileNumbers=[9997778886, 6667778889, 5556667776],
idDetails={adharNo=7645345, panNo=232345, voterId=2323454}
]
YML/YAML Injection
==================
=> It stands for Yet Another MarkUp Language.
=> The extension of the file is .yml or .yaml
=> The biggest limitation of properties file is nodes/level will be repeated in
mulitple keys,especially while
working with common prefix concepts like collection,HAS-A property to support
bulk injection using @ConfigurationProperties.
=> SpringFramework doesnot support yml file/where as SpringBoot support yml
injection
=> SpringBoot framework internally use snakeyml<ver>.jar for processing the yml
file.
application.properties
=====================
emp.info.id=10
emp.info.name=sachin

emp.info.loc=MI
application.yml
===============
emp:
info:
id: 10
name: sachin
loc : MI
Rules while writing yml file
============================
=> same nodes/level in the key should not be duplicated
=> replace "." of each node/level with ":" and write new node in next line with
proper indentation(minimum single space is required)
=> replace "=" symbol with ":" before placing value having minimum single space.
=> To replace Array,List,Set elements use "-".
=> Take Map collection keys and HAS-A property subkeys as the new nodes/levels.
=> use #symbol for Commenting.
application.properties
======================
emp.info.id=10
emp.info.name=sachin
#HAS-A property injection
emp.info.company.name=MI
emp.info.company.location=Bandra
emp.info.company.size=35
#Array object injection
emp.info.skill-set[0]=java
emp.info.skill-set[1]=jee
emp.info.skill-set[2]=ORM
emp.info.skill-set[3]=SpringBoot
#List object injection
emp.info.project-names[0]=IND
emp.info.project-names[1]=World1X
emp.info.project-names[2]=Mumbai
emp.info.project-names[3]=Asia1X
#Set Object injection
emp.info.mobile-numbers[0]=9997778886
emp.info.mobile-numbers[1]=6667778889
emp.info.mobile-numbers[2]=5556667776
emp.info.mobile-numbers[3]=5556667776
#Map object injection
emp.info.id-details.adharNo=7645345
emp.info.id-details.panNo=232345
emp.info.id-details.voterId=2323454
application.yml
===============

emp:
info:
id: 7
name: dhoni
company:
name: iNeuron
location: Bengaluru
size: 35
mobile-numbers:
- 2223334445
- 7776665554
- 5556665554
skill-set:
- java
- jee
- orm
- SpringBoot
project-names:
- WorldX1
- IND
- AsiaX1
- CSK
id-details:
adharNo: 12345
panNo: 1343556
voterId: XUCSA12
eg#2.
application.properties
======================
spring.datasource.url=jdbc:mysql:///octbatch
spring.datasource.username=root
spring.datasource.password=root123
application.yml
===============
spring:
datasource:
url: jdbc:mysql:///enterprisejavabatch
username: root
password: root123
=> Once we have properties file in eclipse, we can convert into yml using sts
supplied plugin.
=> The nodes/level in the keys of properties file/.yml file are not case sensitive.
What is the difference b/w properties file and .yml file?
Properties file
==============
=> no rules and guideliness to develop properties file,just Key=Value
=> it can be used only in java
=> No way related to json format
=> can be used in both Spring and SpringBoot project
=> nodes/level in the keys can be duplicated.
=> it is not a hierarchial data
=> Custom properties file can be injected to bean using @PropertySource
=> While working with profiles in springboot we can't place multiple profiles in
single properties file.

=> Spring/SpringBoot directly loads and reads the content of properties file.
=> use properties file when no of keys are minimal and nodes/level in the key are
not duplicated.
YML file
========
=> specification/rule and guideliness given by www.yml.org
=> can be used in .java,.ruby,.python etc
=> Super set of JSON
=> Supported only by SpringBoot
=> nodes/level in the keys can't be duplicated.
=> Its a hierarchial data
=> Custom files will be configured using @PropertySource and specifying
PropertySource class is required.
=> we can place multiple profiles in single yml file having seperation with "--".
=> every yml file will be converted to property files before loading.
=> use yml file when no of keys are more and nodes/level in the key are repeating.
Realtime DI using application.yml to injection HikariDataSource object in DAO layer
===================================================================================

refer:: BootProj06-RealTimeDIUsingYML

application.yml
===============
spring:
datasource:
password: root123
url: jdbc:mysql:///enterprisejavabatch
username: root

package in.ineuron.comp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.ineuron.dto.Employee;
@Repository
public class EmployeeDaoImpl implements IEmployeeDAO {
private static final String SQL_SELECT_QUERY = "select
eid,ename,eage,eaddress from employee";
@Autowired
private DataSource dataSource;
@Override

public List<Employee> findAllEmployees() throws Exception {
System.out.println("DataSource Connection is :: " +

dataSource.getClass().getName());

List<Employee> empList = new ArrayList<Employee>();
try (Connection connection = dataSource.getConnection();

PreparedStatement pstmt =
connection.prepareStatement(SQL_SELECT_QUERY);

ResultSet resultSet = pstmt.executeQuery()) {
while (resultSet.next()) {
Employee employee = new Employee();
employee.setEid(resultSet.getInt(1));
employee.setEname(resultSet.getString(2));
employee.setEage(resultSet.getInt(3));
employee.setEaddress(resultSet.getString(4));
empList.add(employee);
}
} catch (SQLException se) {
se.printStackTrace();
throw se;
} catch (Exception e) {
e.printStackTrace();
throw e;
}
return empList;
}
}
pom.xml
=======
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
<groupId>com.mysql</groupId>
<artifactId>mysql-connector-j</artifactId>
<scope>runtime</scope>
</dependency>

MAVEN PROJECT MANAGEMENT TOOLS
==============================
What is build process?
Keeping resources ready=> Arranging them in various folders=> add libraries/jar
to classpath => compilation => execution=> testing
=> packing for release or deployment is continuously required in project
development and delivery process.
Core Java Project build process
===============================
a. develop java resources and other files
b. keep them in different folders
c. add jar files to classpath
d. compilation
e. execution/testing[performing testing on his own piece of code is called
"UnitTesting"]
f. packing the app for release.
Performing build process activities manually is having lots of limitations
a. remembering compilated and repeatitive operations is very tough.
b. we may mismatch order.
c. we may forget certain activities
d. doing multiple activities of build process manually will waste the time.
To automate this process activities we can use .bat file
========================================================
run.bat
=======
cd e:
md xyz
cd xyz
copy ... ...
copy ... ...
set path=...
set classpath = ...
javac -d *.java
java <pkg>.<MainClass>
cmd> run.bat
batch file is given to combine all related commands into single command[by using
single command we can automate the process]
limitations of batch files
==========================
a. Conditional execution is not possible.
b. we can not create dependancy among the operation.
c. jar files must be added dynamically no dynamic downloading of jar file from
internet
d. if one command files is batch file .next command will not execute.
e. It is not declarative[ not self intelligent ie, we need to tell everything to
do]
To overcome some of these problems we got ant tool[Another Neat Tool]
a. It is same as batch files, but we can keep operations as conditional
operations and we can create dependency among
the operations.
To overcome both these tools problem we got "Maven" tools with lots of advanced
features

=> Maven is just not a build tool it is also called as "Project Management tool".
KeyFeatures of maven
====================
1. Maven tries to avoid so much configurations as possible by chooosing real
default values and supplying
project templates[archetypes]
2. Can download jars automatically.
3. Can maintatin mulitple repositories having jar files, plugins etc
4. Provides standard project directory structure.
5. Gives maven inheritance to share jar files and plugin among the multiple
projects.
6. Allows to develop multi module projects.
7. Can generate the war,jar,ear file based on the application componenets.
8. Can generate the project documentation.
9. Can run unit tests and can generate unit test reports.
10. Can clean and install the projects in the local servers or remote servers.
Archetypes[Project templates]
=============================
1. maven-archetype-quickstart[for standalone projects]
2. maven-archetype-webapp[for webapplications]
Note: archetypes are project directory structure models
Maven can be build in 2 ways
a. In command line mode
b. From IDE Like Eclipse,Intelij,Netbeans,Eclipselink,....
To keep maven in our system
=================================
a. Download zip file and extract it from the following link

https://maven.apache.org/download.cgi(send one in the link => apache-
maven-3.9.1-bin.zip)

b. Create the following environment variables
a. Add <maven_home>\bin to path environment variables.
set path=D:\jars\apache-maven-3.9.1\bin

b. Add java installation folder to JAVA_HOME environment variables.
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_202

To check whether maven is installed properly or not just type mvn -version in
command prompt
D:\>mvn -version
Apache Maven 3.9.1 (2e178502fcdbffc201671fb2537d0cb4b4cc58f8)
Maven home: D:\jars\apache-maven-3.9.1
Java version: 1.8.0_202, vendor: Oracle Corporation, runtime: C:\Program Files\
Java\jdk1.8.0_202\jre
Default locale: en_IN, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
Maven repositories
==================
=> Repositories is a small db or folder which holds items.
=> Maven repositories can hold
a. jar files/libraries/dependancies
b. plugins(patch software to provide additional functionalities)
c. old/sample projects[Springboot==> Old project==> inherited through =>
Maveninheritance]

In Maven everything (jar/plugin/project) is identified with 3 details[GAV]
a. artifactId(jar file name/plugin name/projectname)
b. groupId(company name)
c. version(jar/plugin/project version)[stable(currently in use),SNAPSHOT(next
version not stable),RELEASE(next version ready)]
eg#1.
GroupId => pivotal team
artifactId => spring-aspects
version => 5.3.17
Repositories
============
a. Central Repositories(given by maven people)
Central Repository:
Avaialble in internet, managed by Apache Maven Community. When Maven does not find
any dependency in local repository, it starts
searching in central repository.
URL for central repository: http://repo.maven.apache.org/maven2
Generally maintains free jars and plugins of open source technologies
b. Local Repositories(In every machine where maven is required)
It is user specific repository, generally it will be collected from TL/PL who
creates Maven project directory structure.
Contains jars, plugins, current project related packings and etc..
Default location: C:\users\<usernmae>\.m2
Will be created automatically for any maven command apart from (mvn -version)
Location can be changed through <maven_home>\conf\settings.xml file using

<localRepository>d:\\maven</localRepository>
c. Remote Repositories(Give by third party companies)
reating an Project using MAVEN in CLI Mode
===========================================
1. Open the Command Prompt and change directory where you want to create your
project and call
mvn archetype:generate (goal to begin the process).
D:\maven>mvn archetype:generate(press enter key)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive
contains): 2036:
Choose org.apache.maven.archetypes:maven-archetype-quickstart version:
1: 1.0-alpha-1
2: 1.0-alpha-2
3: 1.0-alpha-3
4: 1.0-alpha-4
5: 1.0
6: 1.1
7: 1.3
8: 1.4
Choose a number: 8:
Define value for property 'groupId': ineuron
Define value for property 'artifactId': MathProj1
Define value for property 'version' 1.0-SNAPSHOT: : 1.0
Define value for property 'package' ineuron: : in.ineuron
Confirm properties configuration:
groupId: ineuron
artifactId: MathProj1
version: 1.0

package: in.ineuron
Y: :
[INFO] ----------------------------------------------------------------------------

[INFO] Using following parameters for creating project from Archetype: maven-
archetype-quickstart:1.4

[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: ineuron
[INFO] Parameter: artifactId, Value: MathProj
[INFO] Parameter: version, Value: 1.0
[INFO] Parameter: package, Value: in.ineuron
[INFO] Parameter: packageInPathFormat, Value: in/ineuron
[INFO] Parameter: package, Value: in.ineuron
[INFO] Parameter: version, Value: 1.0
[INFO] Parameter: groupId, Value: ineuron
[INFO] Parameter: artifactId, Value: MathProj
[INFO] Project created from Archetype in dir: D:\Mavenpgms\MathProj1
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 05:18 min
[INFO] Finished at: 2023-04-03T12:35:58+05:30
[INFO] ------------------------------------------------------------------------
MathProj1
|=> src/main/java[source code]
|=> in.ineuron

|=> MathApp.java(source code)

|=> src/test/java[test code-> unit testing]

|=> in.ineuron

|=> AppTest.java(test code)
|=> pom.xml(build file)===> groupId,artifactId,version
MathApp.java
============
package in.ineuron;
public class MathApp {
public int add(int x, int y){
return x+y;
}
public static void main( String[] args ){
MathApp m = new MathApp();
int result = m.add(10,20);
System.out.println("The sum is :: "+result);

}
}
In the command prompt execute the following life cycle actions
a. mvn package
>» Generates jar files in target folder having <projectname>-ver. jar file
b. mvn clean
» Cleans the project .. deletes target folder
c. mvn clean package
» Cleans the project and also creates jar file with latest code
d. mvn compile
>> compile the project code and generate the .class file in target folder.
To run jar file App manually

D:\mavenpgms\MathProjl>java -cp target/MathProj1-1.0.jar
in.ineuron.Arithmetic
The Maven Life cycles are
a. clean(3 phases)
b. default(23 phases)
c. site (4 phases)
=> Each life cycle of maven will have lot of phases.
=> These phases are already linked with plugins to peform certain operations, but
we can configure extra plugins to perform
more operations.
To test our code writing unit test case through Junit
=====================================================
package in.ineuron;
import junit.framework.*;
import in.ineuron.*;
public class AppTest extends TestCase{
public void testSumWithPositiveNumber(){
MathApp ar = new MathApp();
int actual = ar.add(10,20);
int expected = 30;
assertEquals(actual,expected);

}
public void testSumWithNegativeNumber(){
MathApp ar = new MathApp();
int actual = ar.add(-10,-20);
int expected = -30;
assertEquals(actual,expected);

}
public void testSumWithMixedNumber(){
MathApp ar = new MathApp();
int actual = ar.add(-10,20);
int expected = 10;
assertEquals(actual,expected);

}
public void testSumWithZero(){
MathApp ar = new MathApp();
int actual = ar.add(0,0);
int expected = 0;
assertEquals(actual,expected);

}
}
D:\Mavenpgms\MathProj1>mvn test

=> runs all the test cases and generates the

report in command line.
D:\Mavenpgms\MathProj1>mvn surefire-report:report

=> go to target folder search for a file called

surefire-report.html
D:\Mavenpgms\MathProj1>mvn site

=> go to target folder search for a file called

index.html
Maven can't execute the java app directly becoz there is no life cycle phases for
that.
To use that we need to use an extra plugin called :: exec-maven-plugin
<build>
<plugins>

<plugin>
<groupId>org.codehaus.mojo</groupId>
<artifactId>exec-maven-plugin</artifactId>
<version>3.1.0</version>
<executions>
<execution>

<id>ArithmeticApp</id>
<phase>package</phase>

<goals>
<goal>java</goal>
</goals>
</execution>
</executions>
<configuration>
<mainClass>in.ineuron.MathApp</mainClass>
</configuration>
</plugin>
</plugins>
</build>
D:\Mavenpgms\MathProj1>mvn clean package
[INFO] Scanning for projects...
[INFO]
[INFO] -------------------------< ineuron:MathProj1 >--------------------------
[INFO] Building MathProj1 1.0
[INFO] from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- clean:3.2.0:clean (default-clean) @ MathProj1 ---
[INFO] Deleting D:\Mavenpgms\MathProj1\target
[INFO]
[INFO] --- resources:3.3.0:resources (default-resources) @ MathProj1 ---
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources,
i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory D:\Mavenpgms\MathProj1\src\main\
resources
[INFO]
[INFO] --- compiler:3.10.1:compile (default-compile) @ MathProj1 ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding Cp1252, i.e.
build is platform dependent!
[INFO] Compiling 1 source file to D:\Mavenpgms\MathProj1\target\classes
[INFO]
[INFO] --- resources:3.3.0:testResources (default-testResources) @ MathProj1 ---
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources,
i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory D:\Mavenpgms\MathProj1\src\test\
resources
[INFO]
[INFO] --- compiler:3.10.1:testCompile (default-testCompile) @ MathProj1 ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding Cp1252, i.e.
build is platform dependent!
[INFO] Compiling 1 source file to D:\Mavenpgms\MathProj1\target\test-classes
[INFO]
[INFO] --- surefire:3.0.0:test (default-test) @ MathProj1 ---
[INFO] Using auto detected provider org.apache.maven.surefire.junit.JUnit3Provider
[INFO]

[INFO] -------------------------------------------------------
[INFO] T E S T S
[INFO] -------------------------------------------------------
[INFO] Running in.ineuron.AppTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.011 s - in
in.ineuron.AppTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- jar:3.3.0:jar (default-jar) @ MathProj1 ---
[INFO] Building jar: D:\Mavenpgms\MathProj1\target\MathProj1-1.0.jar
[INFO]
[INFO] --- exec:3.1.0:java (ArithmeticOperation) @ MathProj1 ---
The sum is :: 300
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 4.162 s
[INFO] Finished at: 2023-04-04T22:59:06+05:30
[INFO] ------------------------------------------------------------------------
D:\Mavenpgms\MathProj1>java -cp target\MathProj1-1.0.jar in.ineuron.MathApp
The sum is ::300
