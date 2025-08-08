# Springboot POC project

This is a POC project using Spring boot for creating an MVC application, h2 (In-Memeory) database and css/html/jquery at the front end.
Automation testing using Fluentlenium.

Following steps are required to setup and run this POC: 
 - Download this project using Git
 - Run maven build
 - Launch the SpringbootApplication.java as a Java program
 - Once the server is up, hit http://localhost:8090/ to launch the application.

Concepts to learn from the Project:
### UI
From the home page, you can migrate to the reservations page http://localhost:8090/examples/
When the examples html page is being loaded, $(document).ready() gets called. 
At this point we call the backend api using Jquery ajax support to fetch all the examples data and render dynamically in a table.
Pay attention to how the path to CSS, js files and libraries is mentioned in the html files.
### Backend
SpringbootApplication.java is responsible for bootstrapping the backend server because of @SpringBootApplication annotation.
The controller classes expose Restful Http resources.
Application uses thymeleaf for rendering the view.
The values set in the Model object are available in the view.
For eg, HomePageRestController.java -> welcome() function returns "Welcome" as a string, which is used to map to welcome.html for rendering the view.
If you want the controller to return a JSON response rather than a view, then add @ResponseBody annotation to the controller function. eg. ReservationController.java -> reservations() method.


### More Information on SpringBoot:

What is Spring Boot? The short answer, is Spring Boot is a library that does a lot work for the user to simplify the implementation of web services.  
Not only is Spring Boot simple to use, it is highly flexible and extensible.  Adding, replacing, or disabling  components is often a matter of configuration.
Spring Boot lets you package up an application in a standalone JAR file, with a full Tomcat server embedded. You can invoke the jar using java -jar jarSnapshot.
The Spring Boot starters use Tomcat as an embedded container by default. So each deployable micro service has its own Tomcat. 
In older days also, you had to deploy your code into an external Tomcat, and applications aren't suppose to share the same Tomcat instance.

Spring boot application can be created from www.start.spring.io
Import it into Eclipse and run maven build (Ensure correct version of Java and Maven are setup in Eclipse)
To change port of application, change in application.properties

CommandLineRunner is an interface provided by spring Boot, and has 1 job: when the application starts up, call the run method passing the var args from main method to to the run. 
Ideally used for batch processing etc, at Spring startup. In this POC, this function is being used to populate in memory DB with data.

Many Spring Boot developers always have their main class annotated with @Configuration, @EnableAutoConfiguration and @ComponentScan. 
Since these annotations are so frequently used together (especially if you follow the best practices above), Spring Boot provides a convenient @SpringBootApplication alternative.

By default, Spring Boot uses Tomcat 7. If you want to use Tomcat 8, just say so! You need only override the Maven build’s tomcat.version property and this 
will trigger the resolution of later builds of Apache Tomcat.
```sh
<properties>
  <tomcat.version>8.0.3</tomcat.version>
</properties>
```
@RepositoryRestResource : this annotation is used to create a HATEOAS service with Spring JPA. 
If you add the @RestController then you have to implement each method that you want to expose on your own and also is does not export this to a HATEOAS format.
HATEOAS (Hypermedia as the Engine of Application State) is a constraint of the REST application architecture.
A hypermedia-driven site provides information to navigate the site's REST interfaces dynamically by including hypermedia (metadata) links with the responses. 

https://spring.io/ website has separate guides each for integrating Spring boot application with Jquery, Angular etc.


### Fluentlenium:

Fluentlenium helps you automate UI functional tests for the browser. It provides a Java fluent interface to Selenium, and brings some magic to avoid common 
issues faced by Selenium users. FluentLenium is shipped with adapters for JUnit, TestNG and Cucumber, but it can also be used standalone.

(Fluentlenium/Selenium are not testing framework though, they are browser automation frameworks. For testing purposes, they are clubbed with 
testing frameworks like Junit)

You can define the URL of the page by overriding the getUrl method.
Then, it’s possible to use the goTo() method in your test code to set the current URL to the URL of the page.
You can also override isAt method, to run all the assertions necessary in order to ensure that you are on the right page.

Fluentlenium provides $ and el as DOM selectors.
Leuentlenium can be used for end to end automation testing with the backend service available, or can be used just for HTML frontend, by mocking the backend 
calls with mock JSON responses.

To learn more on Fluentlenium: http://fluentlenium.org/

### Content negotiation:

By default, springboot returns json output. To enable xml:
1. Add jackson-dataformat-xml in pom
2. Hit the url with the accept header:

```
Header {Accept: application/xml}
http://localhost:8090/examples/findByName/Ramit
```

### HATEOAS: 
HATEOAS stands for Hypermedia As The Engine Of Application State. Used to give additional information in our http responses.

To use HATEOS, include it in pom. See the below url that not only returns the data for the given user, but also returns the link to URI returning all users:

```
http://localhost:8090/examples/findByName/Ramit
```

See ReservationRestController -> findReservationByName() on how to prepare a HATEOAS resource, add links to the resource and returning the same.

### Swagger
Swagger is used to share the contract of your service. Add the swagger as well as the swagger-ui dependency in the pom. Create a swagger config class that creates a bean of type Docket. (see SwaggerConfig.java). Hit the url below to see the swagger UI

```
http://localhost:8090/swagger-ui.html
```
Now hit the url below, and you can see lot of documentation created. This json can be shared with the clients, who can then load it into swagger ui at their end to see all information about our exposed apis.

```
http://localhost:8090/v2/api-docs
```

### Actuator
```
http://localhost:8090/health (spring 2.0 onwards , it's http://localhost:8090/actuator/health
http://localhost:8090/info
```

You can expose more actuators by adding below in application.properties:

```
management.endpoints.web.exposure.include=*
```

### Internationalization
1. Configure locale resolver beans with default locale settings (See LocaleResolverConfig.java)
2. Pass the locale from request headers as in /greet mapping.
3. Hit below url with the headers as configured in step 2. In case no locale is passed, then default locale as configured in 1 gets picked up. Messages file as per the locale gets picked up, and the corresponding message is returned.

```
Accept-Language:FR
http://localhost:8090/greet
```


 