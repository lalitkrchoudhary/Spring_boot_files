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



---------------------------------------------------------------------------------------------------------------------------------------------------------------

Developing Spring boot application using Java Config Annotation
==============================================================
=> These are supplied by JSE,JEE modules(from java9 they are released as
independent libraries)
=> For these annotations, they underlying f/w or container or server decides the
functionality
In Spring, the spring framework will decide the functionality.
In hibernate, the hibernate framework will decide the functionality.
In Servlet, the servlet container will decide the functionality.
eg: @PostConstruct,@PreDestroy,@Named,@Resource,@Inject,.....
@Named:: To configure java class as SpringBean and also to resolve ambiguity.
@Inject,@Resource :: They are alternative to @Autowired for Dependancy Injection.
@Resource can't be applied at constructor level

injection.
Note: Invasive(working to a company with bond) and Non-Invasive Programming(working
to a company without bond)
SpringBean class with Spring supplied annotations like
@Component,@Autowired,@Qualifier make spring bean class as "Invasive".
To make SpringBean class as non-invasive take the support of Java Config
Annotation.
Note:As of now very limited java config are available,so it is practially
impossible to develop entire spring or
spring boot application using java config annotation.
so we prefer the following order
a. Java config annotation
b. Spring annotations
c. third party annotations
d. custom annotations

To use java config annotations we need to add the following jar file
pom.xml
=======
<dependency>
<groupId>javax.inject</groupId>
<artifactId>javax.inject</artifactId>
<version>1</version>
</dependency>
=> @Inject can be used at field level,construtor level,setter method level
=> @Resource can be used at field level,setter method level.
=> While working with @Inject we need to use another annotation called @Named to
resolve the ambiguity problem.
=> While working with @Resource only "name" param itself would resolve the problem.

refer:: BootProj07-DependancyInjection-
JavaConfiguration

Note:
As of Springboot2.5+ version is using two Datasources as a part of
AutoConfiguration if we add spring-boot-starter-jdbc
a. hikari cp(default)

b. Apache dbcp2 datasource(only when hikaricp jars are not there in the
classpath)
c. tomcat-dbc(only when hikaricp jars are not there in the classpath)
Priority order for AutoConfiguration is
a. hikaricp(best choice)
b. tomcat
c. dbcp2
DBCP2
=====
<dependency>
<groupId>org.apache.commons</groupId>
<artifactId>commons-dbcp2</artifactId>
</dependency>
tomcat
======
<!-- https://mvnrepository.com/artifact/org.apache.tomcat/tomcat-jdbc -->
<dependency>
<groupId>org.apache.tomcat</groupId>
<artifactId>tomcat-jdbc</artifactId>
</dependency>
How can we make dbcp2 datasource to work with SpringBoot?
=> exclude hikaricp jar file from dependent jar of "spring-boot-starter-jdbc"
pom.xml
=======
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-jdbc</artifactId>
<exclusions>
<exclusion>
<groupId>com.zaxxer</groupId>
<artifactId>HikariCP</artifactId> ====> go to dependancy

hierarchy tab(right click on hikaricp ,exclude maven artifact)

</exclusion>
</exclusions>
</dependency>
Add apachedbcp2 jar files
pom.xml
=======
<dependency>
<groupId>org.apache.commons</groupId>
<artifactId>commons-dbcp2</artifactId>
</dependency>
Can we disable autoconfiguration of certain spring bean even though starters are
added?
Ans. yes,we can do by using using exclude param

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,

JdbcTemplateAutoConfiguration.class })

public class BootProj06ConfigurationPropertiesAppApplication {
public static void main(String[] args) {

;;;;;

}
}

In the above case we need to use @Bean method to create ur choice class objects and
to make them as spring bean either in @Configuration
class or in @SpringBootApplication class.
@Configuration
public class PersistConfig {
@Autowired
private Environment env;
@Bean
public ComboPooledDataSource createDS() throws Exception {
System.out.println("PersistConfig.createDS()");
ComboPooledDataSource dataSource = new ComboPooledDataSource();
dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
dataSource.setUser(env.getProperty("spring.datasource.username"));
dataSource.setPassword(env.getProperty("spring.datasource.password"));
return dataSource;
}
}
application.yml
===============
spring:
datasource:
password: root123
url: jdbc:mysql:///enterprisejavabatch
username: root
pom.xml
======
<dependency>
<groupId>com.mchange</groupId>
<artifactId>c3p0</artifactId>
<version>0.9.5.4</version>
</dependency>
EmployeeDaoImpl.java
====================
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
}
}
Output

DataSource Connection is :: com.mchange.v2.c3p0.ComboPooledDataSource
Usage of SPEL in realtime
=========================
BillGenerator.java
==================
@Component("bill")
public class BillGenerator {
@Value("#{item.idlyPrice+item.dosaPrice+item.vadaPrice}")//SPEL => used for
computation
private Float billAmount;
@Value("Accord")
private String hotelName;
@Autowired
private ItemsInfo info;
@Override
public String toString() {
return "BillGenerator [billAmount=" + billAmount + ", hotelName=" +

hotelName + ", info=" + info + "]";
}
}
ItemsInfo.java
==============
@Component("item")
public class ItemsInfo {
@Value("${items.info.idlyPrice}")
public float idlyPrice;
@Value("${items.info.dosaPrice}")
public float dosaPrice;
@Value("${items.info.vadaPrice}")
public float vadaPrice;
@Override
public String toString() {
return "ItemsInfo [idlyPrice=" + idlyPrice + ", dosaPrice=" + dosaPrice

+ ", vadaPrice=" + vadaPrice + "]";
}
}
application.properties
======================
items.info.idlyPrice= 10
items.info.dosaPrice= 20
items.info.vadaPrice= 30
BootProj07DependancyInjectionJavaConfigurationApplication.java
==============================================================

@SpringBootApplication
public class BootProj07DependancyInjectionJavaConfigurationApplication {
public static void main(String[] args) throws Exception {
ApplicationContext context = SpringApplication

.run(BootProj07DependancyInjectionJavaConfigurationApplicat

ion.class, args);

System.out.println("Beans info are :: " +
Arrays.toString(context.getBeanDefinitionNames()));

System.out.println();
BillGenerator billGenerator = context.getBean(BillGenerator.class);
System.out.println(billGenerator);
((ConfigurableApplicationContext) context).close();
}
}
output
BillGenerator [billAmount=60.0, hotelName=Accord, info=ItemsInfo [idlyPrice=10.0,
dosaPrice=20.0, vadaPrice=30.0]]



-----------------------------------------------------------------------------------------------------------------------------------------------------

Creating a standalone maven project in Non-Interactive Mode
===========================================================
-DgroupId=ineuron
-Dartifactlid=SwapDemoCmd
-DarchetypeArtifactid=maven-archetype-quickstart
-DinteractiveMode=false
D:\mavenpgms\>mvn archetype:generate -DgroupId=ineuron -DartifactId=SwapDemoApp -
DarchetypeArtifactId=maven-archetype-quickstart
-Dpackage=in.ineuron -Dversion=1.0 -DinteractiveMode=false
SwapDemoApp
src/main/java

|=> in.ineuron.SwapDemoApp(main method)

src/main/test

|=> in.ineuron.App(test case code)

package in.ineuron;
class SwapApp
{
int a,b;
public void accept(int x,int y){
a=x;
b=y;
}
public void swapValues(){
a=a+b;
b=a-b;
a=a-b;
}
void disp(){
System.out.println("Value of a is :: "+a);
System.out.println("Value of b is :: "+b);
}
}
public class SwapDemoApp {
public static void main( String[] args ){

if(args.length!=2)
System.out.println("Plz enter 2 numbers...");
else{
int x1= Integer.parseInt(args[0]);
int x2= Integer.parseInt(args[1]);
SwapApp s = new SwapApp();
s.accept(x1,x2);
System.out.println("Before swapping");
System.out.println("---------------");
s.disp();
s.swapValues();
System.out.println("After swapping");
System.out.println("---------------");
s.disp();
}
}
}
pom.xml[The above program should run from maven and it should accept command line
arguments,so we use a plugin called "exec-maven-plugin"]

=======
<build>
<plugins>
<plugin>
<groupId>org.codehaus.mojo</groupId>
<artifactId>exec-maven-plugin</artifactId>
<version>3.1.0</version>
<executions>
<execution>

<id>ex2</id>
<phase>package</phase>

<goals>
<goal>java</goal>
</goals>
</execution>
</executions>
<configuration>
<mainClass>in.ineuron.SwapDemoApp</mainClass>

<arguments>
<argument>30</argument>
<argument>40</argument>
</arguments>
</configuration>
</plugin>
</plugins>
</build>
D:\Mavenpgms\SwapDemoApp>mvn package
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------< ineuron:SwapDemoApp >-------------------------
[INFO] Building SwapDemoApp 1.1
[INFO] from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.3.0:resources (default-resources) @ SwapDemoApp ---
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources,
i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory D:\Mavenpgms\SwapDemoApp\src\main\
resources
[INFO]
[INFO] --- compiler:3.10.1:compile (default-compile) @ SwapDemoApp ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding Cp1252, i.e.
build is platform dependent!
[INFO] Compiling 1 source file to D:\Mavenpgms\SwapDemoApp\target\classes
[INFO]
[INFO] --- resources:3.3.0:testResources (default-testResources) @ SwapDemoApp ---
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources,
i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory D:\Mavenpgms\SwapDemoApp\src\test\
resources
[INFO]
[INFO] --- compiler:3.10.1:testCompile (default-testCompile) @ SwapDemoApp ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding Cp1252, i.e.
build is platform dependent!
[INFO] Compiling 1 source file to D:\Mavenpgms\SwapDemoApp\target\test-classes
[INFO]

[INFO] --- surefire:3.0.0:test (default-test) @ SwapDemoApp ---
[INFO] Using auto detected provider org.apache.maven.surefire.junit.JUnit3Provider
[INFO]
[INFO] -------------------------------------------------------
[INFO] T E S T S
[INFO] -------------------------------------------------------
[INFO] Running in.ineuron.AppTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.01 s - in
in.ineuron.AppTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- jar:3.3.0:jar (default-jar) @ SwapDemoApp ---
[INFO] Building jar: D:\Mavenpgms\SwapDemoApp\target\SwapDemoApp-1.1.jar
[INFO]
[INFO] --- exec:3.1.0:java (Swap2NumbersApp) @ SwapDemoApp ---
Before swapping
---------------
Value of a is :: 30
Value of b is :: 40
After swapping
---------------
Value of a is :: 40
Value of b is :: 30
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 4.270 s
[INFO] Finished at: 2023-04-05T20:07:36+05:30
[INFO] ------------------------------------------------------------------------
Working in eclipse to create a maven standalone project
=======================================================
Maven Project
=> Dont select checkbox create a simple project
=> Choose archetype as

maven-archetype-quickstart (select version-1.4 from org.apache.maven)

=> provide
groupid : pwskills
artifactid: MavenProject1
version : 1.0
MavenProject1
|=> src/main/java

|=> in.pwskills.JdbcSelectApp(main code)

|=> src/main/test

|=> in.pwskills.AppTest.java
|=> pom.xml(mysql-connectector-java.jar)
JdbcSelectApp.java
==================
package in.pwskills;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class JdbcSelectApp {
public static void main(String[] args) throws Exception {
String url = "jdbc:mysql://localhost:3306/octbatch";
String username = "root";
String password = "root123";
Connection connection = DriverManager.getConnection(url, username,

password);

System.out.println("Connection object is created:: " + connection);
Statement statement = connection.createStatement();
System.out.println("Statement object is created:: " + statement);
String sqlSelectQuery = "SELECT SID,SNAME,SAGE,SADDRESS FROM STUDENT";
ResultSet resultSet = statement.executeQuery(sqlSelectQuery);
System.out.println("ResultSet object is created:: " + resultSet);
System.out.println("SID\tSNAME\tSAGE\tSADDR");
while (resultSet.next()) {
Integer id = resultSet.getInt(1);
String name = resultSet.getString(2);
Integer age = resultSet.getInt(3);
String team = resultSet.getString(4);
System.out.println(id + "\t" + name + "\t" + age + "\t" + team);
}
// Close the Connection
connection.close();
System.out.println("Closing the connection...");
}
}
pom.xml
=======
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
http://maven.apache.org/xsd/maven-4.0.0.xsd">
<modelVersion>4.0.0</modelVersion>
<groupId>pwskills</groupId>
<artifactId>MavenProj1</artifactId>
<version>1.2</version>
<name>MavenProj1</name>
<!-- FIXME change it to the project's website -->
<url>http://www.example.com</url>
<properties>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
<maven.compiler.source>1.8</maven.compiler.source>

<maven.compiler.target>1.8</maven.compiler.target>
</properties>
<dependencies>
<dependency>
<groupId>junit</groupId>
<artifactId>junit</artifactId>
<version>4.11</version>
<scope>test</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
<dependency>
<groupId>com.mysql</groupId>
<artifactId>mysql-connector-j</artifactId>
<version>8.0.31</version>
</dependency>
</dependencies>
<build>
<pluginManagement><!-- lock down plugins versions to avoid using Maven defaults
(may be moved to parent pom) -->
<plugins>
<!-- Executing maven as java program-->
<plugin>
<groupId>org.codehaus.mojo</groupId>
<artifactId>exec-maven-plugin</artifactId>
<version>3.1.0</version>
<executions>
<execution>

<id>JDBCSELECT APP</id>
<phase>package</phase>

<goals>
<goal>java</goal>
</goals>
</execution>
</executions>
<configuration>
<mainClass>in.pwskills.JdbcSelectApp</mainClass>
</configuration>
</plugin>
</plugins>
</pluginManagement>
</build>
</project>
Execution
=========
right click on project-> run=> maven build => type in goals as exec:java and click
on run
output
======
Connection object is created:: com.mysql.cj.jdbc.ConnectionImpl@707f21d6
Statement object is created:: com.mysql.cj.jdbc.StatementImpl@518376cd
ResultSet object is created:: com.mysql.cj.jdbc.result.ResultSetImpl@20117d95
SID SNAME SAGE SADDR
7 dhoni 41 CSK

10 sachin 49 MI
18 kohli 36 RCB
33 pandya 31 GT
99 rahul 31 LSG
Closing the connection...
Maven features
==============
1. properties
In pom.xml file we use properties feature to link to the version of Spring module
version
<properties>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
<maven.compiler.source>1.8</maven.compiler.source>
<maven.compiler.target>1.8</maven.compiler.target>
<spring.version>5.3.26</spring.version>
</properties>
<dependencies>
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-context</artifactId>
<version>${spring.version}</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-jdbc</artifactId>
<version>${spring.version}</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.springframework/spring-orm -->
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-orm</artifactId>
<version>${spring.version}</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-webmvc</artifactId>
<version>${spring.version}</version>
</dependency>
</dependencies>
2. exclusion
In pom.xml file we can use exclusion to exclude particular dependant jar without
excluding a main jar
<dependency>
<groupId>org.hibernate</groupId>
<artifactId>hibernate-core</artifactId>
<version>5.6.15.Final</version>
<exclusions>
<exclusion>
<groupId>org.jboss.logging</groupId>
<artifactId>jboss-logging</artifactId>

</exclusion>
</exclusions>
</dependency>
Working with repositories
=========================
1. local repository
2. central repository
3. remote repository
refer: MavenProj1
pom.xml(mysqlconnector from central repository)
===============================================
<!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
<dependency>
<groupId>com.mysql</groupId>
<artifactId>mysql-connector-j</artifactId>
<version>8.0.32</version>
</dependency>
pom.xml(oracleconnector from central repository)
================================================
<!-- https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc8 -->
<dependency>
<groupId>com.oracle.database.jdbc</groupId>
<artifactId>ojdbc8</artifactId>
<version>19.12.0.0</version>
</dependency>
pom.xml(oracleconnector from remote repository)
================================================
<!-- https://mvnrepository.com/artifact/com.oracle.jdbc/ojdbc8 -->
<dependency>
<groupId>com.oracle.jdbc</groupId>
<artifactId>ojdbc8</artifactId>
<version>12.2.0.1</version>
</dependency>
<repositories>
<repository>
<id>sample</id>
<url>https://broadinstitute.jfrog.io/artifactory/libs-release-local/</

url>
</repository>
</repositories>
Creating a local repository in .m2 folder for oracle jar
=======================================================
D:\Mavenpgms\SwapDemoApp>mvn install:install-file -Dfile=C:\oraclexe\app\oracle\
product\11.2.0\server\jdbc\lib\ojdbc6.jar -DgroupId=nitinineuron -
DartifactId=nitinineuronoracleljar -Dversion=11.2 -Dpackaging=jar
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------< ineuron:SwapDemoApp >-------------------------
[INFO] Building SwapDemoApp 1.1
[INFO] from pom.xml

[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- install:3.1.0:install-file (default-cli) @ SwapDemoApp ---
[INFO] pom.xml not found in ojdbc6.jar
[INFO] Installing C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6.jar
to C:\Users\nitin\.m2\repository\nitinineuron\nitinineuronoracleljar\11.2\
nitinineuronoracleljar-11.2.jar
[INFO] Installing C:\Users\nitin\AppData\Local\Temp\
mvninstall5098441414756102901.pom to C:\Users\nitin\.m2\repository\nitinineuron\
nitinineuronoracleljar\11.2\nitinineuronoracleljar-11.2.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.320 s
[INFO] Finished at: 2023-04-05T22:10:27+05:30
[INFO] ------------------------------------------------------------------------
In our project pom.xml file we need to use
==========================================
<dependency>

<groupId>nitinineuron</groupId>

<artifactId>nitinineuronoracleljar</artifactId>
<version>11.2</version>
</dependency>
Maven Inheritance
==================
In one project pom.xml, we can add <parent> tag having other project info like
<groupld>, <artifactld> and <version> to get
other project dependencies and plugins.
=> Create one project called HBProj-01 it will have groupId,artifactId,version
pom.xml
=======
<groupId>pwskills</groupId>
<artifactId>HBproj-01</artifactld>
<version>1.1</version>
<properties>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
<maven.compiler.source>1.8</maven.compiler.source>
<maven.compiler.target>1.8</maven.compiler.target>
<hibernate-version>5.6.15.Final</hibernate-version>
<mysql-version>8.0.32</mysql-version>
</properties>
<dependencies>
<dependency>
<groupId>junit</groupId>
<artifactId>junit</artifactId>
<version>4.11</version>
<scope>test</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
<dependency>
<groupId>org.hibernate</groupId>
<artifactId>hibernate-core</artifactId>

<version>${hibernate-version}</version>
</dependency>
<!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
<dependency>
<groupId>com.mysql</groupId>
<artifactId>mysql-connector-j</artifactId>
<version>${mysql-version}</version>
</dependency>
</dependencies>
=> Create one more project called HBProj-02(child project)
pom.xml
=======
<parent>
<groupId>iNeuron</groupId>
<artifactId>HBproj-02</artifactld>
<version>1.2</version>
</parent>

refer: Hibernate-01, Hiberante-02

To see the nature of inheritance check for Hibernate-02 dependancies


------------------------------------------------------------------------------------------------------------------------------------------
PROFILE CONCEPT
===============
working with yaml file for spring boot profiles
===============================================
application.yml
===============
spring:
profiles:
active: dev
application-dev.yml
==================
spring:
datasource:
driver-class-name: com.mysql.cj.jdbc.Driver
password: root123
url: jdbc:mysql:///enterprisejavabatch
username: root
application-test.yml
====================
spring:
datasource:
driver-class-name: com.mysql.cj.jdbc.Driver
password: root123
type: org.apache.commons.dbcp2.BasicDataSource
url: jdbc:mysql:///enterprisejavabatch
username: root
application-uat.yml
===================
spring:
datasource:
driver-class-name: oracle.jdbc.driver.OracleDriver
password: root123
url: jdbc:oracle:thin:@localhost:1521:XE
username: System
application-prod.yml
====================
spring:
datasource:
driver-class-name: oracle.jdbc.driver.OracleDriver
password: root123
url: jdbc:oracle:thin:@localhost:1521:XE
username: System
pom.xml
======
<dependencies>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
<groupId>com.mysql</groupId>
<artifactId>mysql-connector-j</artifactId>
<scope>runtime</scope>
</dependency>

<dependency>
<groupId>com.oracle.database.jdbc</groupId>
<artifactId>ojdbc8</artifactId>
<scope>runtime</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/com.oracle.database.jdbc/ucp -->
<dependency>
<groupId>com.oracle.database.jdbc</groupId>
<artifactId>ucp</artifactId>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-dbcp2 -->
<dependency>
<groupId>org.apache.commons</groupId>
<artifactId>commons-dbcp2</artifactId>
</dependency>
<!-- https://mvnrepository.com/artifact/com.mchange/c3p0 -->
<dependency>
<groupId>com.mchange</groupId>
<artifactId>c3p0</artifactId>
<version>0.9.5.5</version>
</dependency>
</dependencies>
@SpringBootApplication
public class BootProj11RealTimeDiAutoConfigurationProfilesApplication {
@Autowired
private Environment env;
@Bean(name = "dataSource")
@Profile({"dev","uat"})
public ComboPooledDataSource createDS() throws Exception {
System.out.println("BootProj11RealTimeDiAutoConfigurationProfilesApplication.create
DS()");

ComboPooledDataSource dataSource = new ComboPooledDataSource();
dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
dataSource.setUser(env.getProperty("spring.datasource.username"));
dataSource.setPassword(env.getProperty("spring.datasource.password"));
return dataSource;
}
}
working with single yaml file
=============================
spring:
datasource:
driver-class-name: com.mysql.cj.jdbc.Driver
password: root123
url: jdbc:mysql:///enterprisejavabatch
username: root
profiles:
active: dev
---

spring:
datasource:
driver-class-name: com.mysql.cj.jdbc.Driver
password: root123
url: jdbc:mysql:///enterprisejavabatch
username: root
config:
activate:
on-profile: dev
---
spring:
datasource:
driver-class-name: oracle.jdbc.driver.OracleDriver
password: root123
url: jdbc:oracle:thin:@localhost:1521:XE
username: System
config:
activate:
on-profile: test
---
spring:
datasource:
driver-class-name: com.mysql.cj.jdbc.Driver
password: root123
type: org.apache.commons.dbcp2.BasicDataSource
url: jdbc:mysql:///enterprisejavabatch
username: root
config:
activate:
on-profile: uat
---
spring:
datasource:
driver-class-name: oracle.jdbc.driver.OracleDriver
password: root123
url: jdbc:oracle:thin:@localhost:1521:XE
username: System
config:
activate:
on-profile: prod
Runners in springboot
=====================
=> Runners are java classes which would act like Spring bean of SpringBoot which
are implementin XxxxRunners directly or indirectly.
=> Every runner class contains run() dealing with onetime executing logic and those
logics will execute where SpringApplication.run()
is about to complete all its startup activities(right after creating a
ApplicationContext object and completing
preinstantiation and injections)
=> The run() of every Runner class will be executed automatically by IOC container
only for one time as a part of ApplicationStartup
process that takes place in SpringApplication.run() method.
There are 2 types of Runners in SpringBoot
a. CommandLineRunner(I)
b. ApplicationRunner(I)

Note: Both the runner are giving run() as the callback method[becoz it get called
by underlying IOC container automatically]
Both the runner run() method gets the command line arguments as the parameter
values but the way they get them is
different.


Log4J SL4J and Maven Multi Module
=================================

Maven[GAV]
=====
Maven Repositories

a.local(.m2) 2.Central(http://maven....) 3. RemoteRepository(3rd party

team)
Maven archetype[quickstart,webapp]
Maven LifeCycle

a. clean b.default 3.site

Maven features

a. properites b. exclusions
Maven inheritance[<parent><GAV></parent>]
Maven MultiModule
While working with archetype webapp,we need to inform maven about
a. servlet-api
b. jstl-api
c. hibernate-api in pom.xml file
since it is webapplication, to use jstl inside jsp we need to inform the namespace
inside web.xml file as shown below.
web.xml
=======
<web-app
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns="http://java.sun.com/xml/ns/javaee"
xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
id="Your_WebApp_ID"
version="2.5">
</web-app>
Maven Multi-Module Project
==========================
Whatever project we created so far are single module projects. The concept of
Multi-Module Project is very simple.
When various modules are part of a project and closely related to each other, then
the project should be structured as a
multi module project.
In a multi module project, Maven ensures that all sub modules are built in proper
order before the main module build.
According to agile methodology, we take muitiple layers as multiple modules in a
project.
All DAO classes together in one module, all service classes together in one module
and all web components together in one
module. To develop such projects in maven, we can take maven multi-module project.
MavenMultiModule(pom)===>in.ineuron
|=> WishServiceModule(jar)
com.pwskills.service

|=> WishMessageService.java

|=> WishWebModule(war)
|
src/main/java
|=> com.lco.controller
|=>webapp

|=> WEB-INF

|=> index.jsp(landing page)

WishMessageService.java
=======================
package com.pwskills.service;
public class WishMessageService {
public String wishMsg(String user) {
return "Good Morning :: " + user;
}
}
index.jsp
=========
<%@ page import="com.pwskills.service.WishMessageService"%>
<h1 style='color:red; text-align: service'>
<%= new WishMessageService().wishMsg("AlakPandey")%>
</h1>

Log4J and SL4J
==============
What is Logging?
The process of keeping track of application's flow of execution is called
"Logging".
using logging generated log messages we can find the state of the application
execution in any given date and time
logging keeps track of components and code that are involved in application
execution.
Auditing
=======
logging keeps track of various components and code that are involved in execution
of application.
[getting classname,methodnames,blocks,modules that are involved in
application execution]
Auditing keeps track of various activities done by the user while operating the
application.
[getting the activities like usersignedin,opened inbox, repliedmail,.....
signedout]
Note: Auditing is one of the use case of "logging".
The special log messages of logging that keeps track of user activities w.r.t
application execution is called "Auditing".
Use case of Logging
===================
=> While performing unit testing,if the test results in negative we need to debug
the code to know the reason, in that case log
messages are useful for developers
=> While fixing the bugs given by tester,the developers needs to know the state of
the application execution that needs the
support of log messages.
=> After releasing the project,we get production bugs from clients org given by end
users through onsiteteam, the offshore team
uses the log messages to know the state of the execution when bug was rised
through log messages.

Note:
onsite =>go to client org location to recieve,install,maintain the project
offsite =>stays in the software company to release the project and fix the
production bugs given by client org.

offsite team is a supporting team to onsite team.

While maintaining the project there will be production environment,if the project
is down all of a sudden then the maintainence
team sends the exception related to special log file to know the reason and fix the
problems.
while taking the backup of DBS/w and bringing the DB s/w back to normal software
after crash, we take the support of logs.
While performing transaction support, we need the support of log messages.
Every project contains the following 4 environments
a. Dev environment.
b. Testing environment.
c. UAT environment.
d. Production environment.
UAT,Production => belongs to Client organisation
Note: The code related to logging will be added to project during devleopment only,
but it will used in different phases of the project.
We can do logging in java applications using
a. System.out.println()
b. System.err.println()
but the above 2 methods are having limitations
a. We cannot write log messages only to the console monitor while will be lost
after some amount of time.
b. We cannot categorize log messages
c. We cannot format log messages
d. We cannot write log messages to different destinations like
file,dbs/w,mailserver etc....
e. We cannot see old log messages after few days/hours(particular date and time log
messages)
f. We cannot filter log messages while retreiving
g. Writing messages to console monitor using System.out.pritln() is a single
threaded process,so this is not suitable in case
of web environment(webapps).
To overcome this problems we need to go for
a. JavaAssertions (from JDK SunMS)
b. Javalogging api given in java.util package(from JDK SunMS)
c. commons logging(apache)
d. jboss logging(redhat)
e. log4j(apache => best in market)
f. logback (adobe)
SL4J
-> It stands for Standard Simple logging facade for Java
-> It provides abstraction on mulitple logging api/tools/framework and provides
unified api for logging by internally using
our choice logging api.
SL4J(framework)[SUNMS]

log4j
logback
commons-logging
Log4j
=====
type : logging tool for java
version : 1.X(stable) 2.X(not stable)
vendor : apache
open source
jar file representing api: log4j-<ver>.jar(download from maven repository)
To download log4j s/w::
https://www.apache.org/dyn/closer.cgi/logging/log4j/1.2.17/log4j-1.2.17.zip
Log4j Advantages
================
1. Allows to categorize the log messages and we can add priorities for log
messages.

DEBUG<INFO<WARN<ERROR<FATAL

Use DEBUG level for normal confirmation code flow statements
eg: main() method start,main() end ,start of b.method and end of b.method
etc...
Use INFO level for important confirmation of code flow statements
eg: connection established with DB s/w, login successfull, OTP
generated,.....
Use WARN level to write log messages for code that should not used/executed but
some home used and executed.
eg: especially useful when we used deprecated api's/poor api's on temporary
basis.
Use ERROR level to write log messages from know exceptions related to catch blocks
like

(SQLException e),catch(IllegalArgumentException e)

Use FATAL level to write log messages from unknow exceptions related catch blocks
like (Exception e),catch(Throwable t) etc.
In testing environment what is the difference b/w bug and issue?
bug => code exists, but the expected functionality is not coming(wrong logic)
eg: click on home hyperlink,redirecting to aboutus page.

issue=> feature/functionality is missing.
2. Allows us to write/record log messages to different destinations like
console,files,dbs/w,mailserver etc.
3. Allows us to format log messages using different layouts like (HTML layout,
XMLLayout...)
4. Allows to retreive log messages using filters
ALL<DEBUG<INFO<WARN<ERROR<FATAL<OFF

Note: In realtime for every app 2 log-files will be maintained
a. Common log files(records all logs messages end to end)
b. Exception log files(records only ERROR and FATAL level log

messages.. useful when system/project is down)
5. Can change the inputs of the app related to log4j either using properties file
or xml files.

6. Log4j can write log messages to files/console/other destination as parallel
process.
7. It is industry standard.
Three important object of Log4j Programming
===========================================
a. Logger object
b. Appender object
c. Layout object
Logger Object
=============
=> enables logging on the given java class.
=> Logger logger = Logger.getLogger(current java class related to
java.lang.Class object);
eg: Logger logger = Logger.getLogger(BankAppProject.class);
=> use the following method based on the priority level to generate the log
message

logger.debug("");
logger.info("");
logger.warn("");
logger.error("");
logger.fatal("");

=> Allows to specify logger level to retrieve log messages

logger.setLevel(Level.DEBUG)[if no logger level is given then the

defualt logger level is DEBUG]
=> Both Appender object and Layout object will be added to logger object
directly or indirectly.
=> Instructions/Inputs to logger object can be hardcoded or given by using
properties file/xml file.
Appender Object
===============
=> specifies the destination where to write/record the log messages.

eg:

FileAppender,RollingFileAppender,DailyRollingFileAppender,JDBCAppender,
IMAPAppender,ConsoleAppender
=> All Appender classes implements from org.log4j.Appender(I).
Layout Object
=============
=> Given to format the log messgaes before givinig to appender for
recording/writing the destination file

eg: SimpleLayout,HtmlLayout,XmlLayout,PatternLayout......

=> All Layout classes extends from org.log4j.Layout(c)
Log4j Architecture
==================

refer diagram

step1: Create a maven standalone project
step2: Add the following dependancy to pom.xml file
pom.xml

=======
<!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
<dependency>
<groupId>com.mysql</groupId>
<artifactId>mysql-connector-j</artifactId>
<version>8.0.32</version>
</dependency>
<!-- https://mvnrepository.com/artifact/log4j/log4j -->
<dependency>
<groupId>log4j</groupId>
<artifactId>log4j</artifactId>
<version>1.2.17</version>
</dependency>
<!-- pluging to make maven run a java code -->
<plugin>
<groupId>org.codehaus.mojo</groupId>
<artifactId>exec-maven-plugin</artifactId>
<version>3.1.0</version>
<executions>
<execution>

<id>JDBCAPP</id>
<phase>package</phase>

<goals>
<goal>java</goal>
</goals>
</execution>
</executions>
<configuration>
<mainClass>in.ineuron.JdbcApp</mainClass>
</configuration>
</plugin>
package in.ineuron;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//classes related to log4j
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
public class JdbcApp {
private static Logger logger = Logger.getLogger(JdbcApp.class);
static {
SimpleLayout layout = new SimpleLayout();
ConsoleAppender appender = new ConsoleAppender(layout);
logger.addAppender(appender);
logger.setLevel(Level.ERROR);
}

public static void main(String[] args) {
logger.debug("start of main method...");
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
try {
Class.forName("com.mysql.cj.jdbc.Driver");
logger.debug("Driver class loaded succesfully...");
String url = "jdbc:mysql:///octbatch";
String user = "root";
String password = "root";
connection = DriverManager.getConnection(url, user, password);
logger.info("connection established");
statement = connection.createStatement();
logger.debug("statement object created...");
String sqlSelectQuery = "select sid,sname,sage,saddress from

student";

resultSet = statement.executeQuery(sqlSelectQuery);
logger.info("Query is executed and ResultSet object is created");
while (resultSet.next()) {
System.out.println(resultSet.getInt("sid") + "\t" +

resultSet.getString("sname") + "\t"

+ resultSet.getInt("sage") + "\t" +

resultSet.getString("saddress"));

}
} catch (ClassNotFoundException c) {
logger.error("Failed to load the driver");
} catch (SQLException se) {
logger.error("Some db problem " + se.getMessage() + "----> " +

se.getErrorCode());

} catch (Exception e) {
logger.fatal("Some unknown exception occured...");
} finally {
try {
if (resultSet != null) {
resultSet.close();
}
} catch (SQLException e) {
logger.error("Problem in closing resultSet");
}
try {
if (statement != null) {
statement.close();
}
} catch (SQLException e) {
logger.error("Problem in closing statement");
}
try {
if (connection != null) {
connection.close();
}
} catch (SQLException e) {
logger.error("Problem in closing connection");

}
}
logger.debug("end of main method");
}
}
right click on maven=> run as maven build -> in goals section type as exec:java
