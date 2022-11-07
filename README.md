# IPlytics coding challenge
This coding challenge is for candidates for the position "Backend Developer - Web Applications"

## Overview
Our software lets users search for Patents and Standards, amongst other things. Our web application is the gateway to our data sets, and we would like to see how well you can build an application that models a little part of our data universe.

We want to know how well you can work with the following:
* Spring Boot framework
* Modern Java API's
* RESTful API design
* Unit and integration testing
* Written and generated documentation

## What we provide you
Fork this repo or clone it to your local environment. You will have a fully working Spring Boot webapp, which you can run and access at http://localhost:8080 to use a very simple API. Make a `GET` request to http://localhost:8080/patents/US1234A1 to load a dummy patent we created for you to get started.

The repo also contains some SQL scripts managed with Flyway, to manage our data model and contents. Data is stored in an in-memory H2 database which is recreated when the application starts up, so you don't need to configure anything external.

Run it from the entry point in the class `CodingchallengeBackendWebappApplication`.

## What we want you to do

You must solve a few small challenges in order to pass our test. In addition to doing the tasks below, we want to see that you can:
* Write clean code and structure it well
* Cover your code with tests in a suitable manner
* Write good commit messages and break up your work into sensibly-sized chunks
* Add documentation where necessary. This could be handwritten or auto-generated from your code

Here are your tasks:

### 1. Fix an endpoint bug
There is a bug in the `/patents/{publicationNumber}` GET endpoint. When we request a patent that we don't have in our database (For example, http://localhost:8080/patents/I-DO-NOT-EXIST ), the endpoint should return a `404 NOT FOUND` but that's not working. Instead we see a `500 INTERNAL SERVER ERROR`.

We already have a failing test case to cover this, in the class: `PatentControllerIntegrationTest`.

* Make the test pass, and try do it in a way which makes the best use of the Spring Framework's features.

### 2. CRUD for patents 
Our users need to add their own patents to the system. We have the `R` of CRUD but not the rest! 

* Add new endpoints to the `PatentController` so we can create new patent entries, or update/delete existing ones. Make sure to use the best practices of RESTful API design, to return sensible responses, and the correct status codes as required. You should also add integration and unit tests as needed.

### 3. Handle Declarations
One interesting aspect of industrial or technology standards is how they are composed. Companies or organisations typically work with a standard-setting organisation (like ISO or ETSI) to "declare" their patent to be a necessary part of a standard. So there is a relational connection between patents and standards, which we can model as a "declaration".

* Create a SQL model for a declaration, and include the appropriate foreign key relationships.

* Create new endpoint(s) which let a user declare their patent(s) to be part of a standard. As in the previous task, ensure that appropriate responses and status codes are returned.

### 4. Setup and query SOLR
We use SOLR internally as our search engine. Given the `/patents/search` endpoint, implement a query to SOLR and return all patents matching the title string parameter.

#### 4.1 Setup SOLR locally

To setup SOLR locally, you first need to have docker installed. Once done, run the following commands to build a SOLR docker container:

`docker run --name solr_9 -d -p 8983:8983 -t solr` <br/> <br/>
`docker exec -it --user=solr solr_9 bin/solr create_core -c patents` <br/> <br/>

`curl -X POST -H 'Content-type:application/json' --data-binary '{
"add-field":{
"name":"title",
"type":"string",
"stored":true ,
"indexed":true },
"add-field":{
"name":"publicationNumber",
"type":"string",
"stored":true ,
"indexed":true },
"add-field":{
"name":"publicationDate",
"type":"string",
"stored":true ,
"indexed":true },
}' http://localhost:8983/solr/patents/schema`
<br/><br/>
`docker cp ./src/main/resources/data/patents.json {container_id}:/opt/solr-9.0.0` <br/>
make sure to replace the `{container_id}` with the id of your SOLR docker instance. <br/> <br/>
`docker exec -it --user=solr solr_9 bin/post -c patents patents.json` <br/> <br/>

To make sure everything is working as expected, navigate to http://localhost:8983. You should be redirected to the SOLR Admin UI.

#### 4.2 Query SOLR

Now that SOLR is up and running, it's time to start querying!

We already loaded our patents SOLR core with sample documents. All that's required is to modify the `searchPatentByTitle` method in the `PatentsService` class and write some code. We already provide a SearchProvider class that handles the communication, you only have to build the query (make sure to use the JSONQueryRequest class) and extract the response.

#### 4.3 Refactor Implementation

* Our SearchProvider class could use some work. Currently, it is a singleton and managed by us instead of being managed by the Spring IOC. Refactor the SearchProvider to be managed by Spring.
* We have some TODOs that our developers were too lazy to get back too. We could use your help in finalizing them

## How to submit
Once you're happy with your work and want to submit, `zip` the repo folder and submit it via email to our HR manager. Remember to include the whole assignment directory (including hidden files), so we can see your git commit history.

Best of luck and thanks for taking the time to complete this challenge.