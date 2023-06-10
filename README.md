# GitHubChecker
Checker for user GitHub repositories info
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Cases](#cases)

## General info
This is a RESTful API written in Eclipse IDE with Java 17 and Spring Boot 3.1.0.
When running it on embedded TomCat local server you can check some
information about GitHub user's repositories information.
	
## Technologies
Project is created with:
* Java: 17
* Spring Boot: 3.1.0
* Apache Maven 3.9.2
	
## Setup
To run this project, you can open it inside an IDE or using Maven and Java.<br />
Download source code and navigate into it.<br />
```
$ cd ../github_checker
$ mvn package
$ java -jar target/github_checker-0.0.1-SNAPSHOT.jar
```
You should see a Spring application starting up and after a few seconds you can test this api yourself.<br />

## Cases
* Given existing username who has at least one non-forked repository and a header "Accept: application/json"<br />
You can test this case with url: localhost:8080/username or by using Postman and creating GET request with the same url<br />
Example results:
```
[
    {
        "name": "some-repo",
        "login": "myNickName",
        "branches": [
            {
                "name": "main",
                "sha": "8d90a7b3e9bf4c8a24ba6d97164fbed64b61fe03"
            }
        ]
    },
    {
        "name": "other-repo",
        "login": "myNickName",
        "branches": [
            {
                "name": "testing",
                "sha": "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b"
            },
            {
                "name": "something",
                "sha": "aa0be1cfae626f5d8b03825011df6261e282ae8f"
            },
            {
                "name": "master",
                "sha": "9834876dcfb05cb167a5c24953eba58c4ac89b1a"
            }
        ]
    }
]
```
They follow form:
```
{
    Repository name
    Owner login
    List or branchers [
        {
            Branch name
            Branch last commit sha
        }
    ]
}
```
* Given non-existing username<br />
What we want here is a response status 404 with a body:
```
{
    "status": ${responseCode}
    "Message": ${whyHasItHappened}
}
```
Example of this is done by a GET Request with url: localhost:8080/nonexistsyes<br />
Response:
```
{
    "status": 404,
    "message": "User was not found"
}
```
* Given header "Accpet: application/xml"<br />
Turn off default Accept header in Postman and add the one above. Then this is the response:
```
{
    "status": 406,
    "message": "Xml Format is not acceptable with this request"
}
```
