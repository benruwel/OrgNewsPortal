




<!-- PROJECT LOGO -->
<br />
<p align="center">
<img src="https://img.icons8.com/doodle/480/000000/news.png" width="200" height="200"/>  

  <h3 align="center" style="color:yellow" >Organisational News Portal</h3>

  <p align="center">
    REST API for querying and retrieving scoped news and information within an organization
</p>
<p align="center">Ben Ruwel</p>



<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
* [BDD](#bdd)
* [Contributing](#contributing)
* [License](#license)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)



<!-- ABOUT THE PROJECT -->
## About The Project

This is a REST API that is used to post news, view information of departments and its employees(users). It has features that allows one access specific info about users and departments, view a list of all the users in a department and also the list of departments a user is associated with. 


### Built With
This is program is wholely written in Java and built with
* [Oracle Java 11.8](https://www.oracle.com/java/)
* [Gradle 6.5](https://gradle.org/)
* [Spark 2.7.2](http://sparkjava.com/)
* [Postgres 42.2.5](https://www.postgresql.org/)
* [Gson 2.8.6](https://github.com/google/gson/blob/master/UserGuide.md)
* [Apache Maven 2.3.2](https://maven.apache.org/)
* [Heroku Plugin 3.0.2](https://elements.heroku.com/addons)
* [JUnit4](https://junit.org/junit5/)



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple example steps.

### Prerequisites

In order to build this app locally, first make sure you have all the programs listed in [Built with](#built-with). Some can be added as dependencies, please check with the build tool you wish to use.o

### Installation

1. Clone the repo
    ```sh
    $ git clone https://github.com/benruwel/OrgNewsPortal.git
    ```
2. If you don't have Postgres installed on your local PC visit [this to install it](https://www.postgresql.org/download/)

3.  Enable gradle to import all the dependencies automatically


## Usage

This api is hosted on Heroku, so you can choose to consume using this link [https://org-news-portal.herokuapp.com/](https://org-news-portal.herokuapp.com/)

These are the REST api routes:

<table>
  <tr>
    <th>Behavior</th>
    <th>Path</th>
    <th>Http Verb</th>
  </tr>
  <tr>
    <td>Create a new departmetnt</td>
    <td>/department/new</td>
    <td>POST</td>
  </tr>
  <tr>
    <td>Delete a specific department</td>
    <td>/department/:department_id</td>
    <td>DELETE</td>
  </tr>
  <tr>
    <td>View info a specific department</td>
    <td>/department/:departmetn_id</td>
    <td>GET</td>
  </tr>
  <tr>
    <td>Get a list of all departments</td>
    <td>/departments</td>
    <td>GET</td>
  </tr>
  <tr>
    <td>Create a new user/employee</td>
    <td>/users/new</td>
    <td>POST</td>
  </tr>
  <tr>
    <td>Get a list of users</td>
    <td>/users</td>
    <td>GET</td>
  </tr>
  <tr>
    <td>Post news about a specific department</td>
    <td>/departments/:department_id/news/new</td>
    <td>POST</td>
  </tr>
  <tr>
    <td>Get a list of the news relating to specific department</td>
    <td>/departments/:department_id/news</td>
    <td>GET</td>
  </tr>
  <tr>
    <td>Add a user to a department</td>
    <td>departments/:department_id/user/:user_id/new</td>
    <td>POST</td>
  </tr>
  <tr>
    <td>View a list of the employees/users in a department</td>
    <td>/departments/:department_id/users</td>
    <td>GET</td>
  </tr>
  <tr>
    <td>View list of departments an employee is associated with</td>
    <td>/user/:user_id/departments</td>
    <td>GET</td>
  </tr>
</table>

You may also choose to build this project locally and consume the api with the localhost port with the following steps:

1. Open your preferred terminal 

2. You will need to create the databases with a specific schema, navigate to src/main/resources/DB and run this command
    ```
    $ psql < create.sql
    ```
    This creates org_news_portal (developmetn db) and org_news_portal_test (test db) on your local machine

    If in any instance you will require to delete the databases, run this:
    ```sh
    $ psql < drop.sql
    ```

3. Then to run the project, navigate to your project's root directory run the project with
    ```sh
    $ gradle run
    ```
4. Open postman and use the _`localhost:4567`_ URL to test the endpoints stipulated above.

## BDD

This project uses Gson to change Java objects to JSON format, which is the golden standard for most apis. 

POST method behavior:

* User fires a POST method e.g. _**POST department/new**_, though the data may differ according to the given body object, there will be a similar 201 response with the generated Json object.
    ```json
    {
        "name": "HR",
        "id": 2,
        "description": "Comfortable workspaces to thrive in",
        "number_of_employees": 2
    }
    ```

GET method behavior

* User fires a GET method e.g. _**GET /departments**_, in this case, the response will be a Json object with an array of all the departments.
    ```json
    [
        {
            "name": "Software Dev",
            "id": 1,
            "description": "Hot Java all day, both coffee and code",
            "number_of_employees": 10
        },
        {
            "name": "HR",
            "id": 2,
            "description": "Comfortable workspaces to thrive in",
            "number_of_employees": 2
        }
    ]
    ```

## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

My email  - ruwelmwangi@gmail.com

<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [IntelliJ](https://www.jetbrains.com/idea/)
* [Icons8](https://icons8.com/icons)
* [Postman](https://www.postman.com/)
