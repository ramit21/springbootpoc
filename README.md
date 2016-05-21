This is a POC project using Spring boot for creating an MVC application, h2 (In-Memeory) database and css/html/jquery at the front end.

To run this POC, download this project, run the maven build, and then launch the SpringbootApplication.java as a Java program. 
Once the server is up, hit http://localhost:8090/ to launch the application.

Concepts to learn from the Project:

UI:
From the home page, you can migrate to the reservations page http://localhost:8090/reserve/
When the reservation html page is being loaded, $(document).ready() gets called. 
At this point we call the backend api using Jquery ajax support to fetch all the reservation data and render dynamically in a table.
Pay attention to how the path to CSS, js files and libraries is mentioned in the html files.

Backend:
SpringbootApplication.java is responsible for bootstrapping the backend server because of @SpringBootApplication annotation.
The controller classes expose Restful Http resources.
Application uses thymeleaf for rendering the view.
The values set in the Model object are available in the view.
For eg, HomePageRestController.java -> welcome() function returns "Welcome" as a string, which is used to map to welcome.html for rendering the view.
If you want the controller to return a JSON response rather than a view, then add @ResponseBody annotation to the controller function. eg. ReservationController.java -> reservations() method.


===============================================================================

More Information on SpringBoot:

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
<properties>
  <tomcat.version>8.0.3</tomcat.version>
</properties>

@RepositoryRestResource : this annotation is used to create a HATEOAS service with Spring JPA. 
If you add the @RestController then you have to implement each method that you want to expose on your own and also is does not export this to a HATEOAS format.
HATEOAS (Hypermedia as the Engine of Application State) is a constraint of the REST application architecture.
A hypermedia-driven site provides information to navigate the site's REST interfaces dynamically by including hypermedia (metadata) links with the responses. 

https://spring.io/ website has separate guides each for integrating Spring boot application with Jquery, Angular etc.

TODO:
Check JMX integration

