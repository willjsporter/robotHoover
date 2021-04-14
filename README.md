# Robot Hoover

## What am I?
This is a project that sets out an area and a starting point for a robot hoover, and gives the hoover instructions for where it should move based on instructions that are a series of commands telling the hoover to move one step either north, south, east or west.
If the instructions attempt to take the hoover out of the bounds of the defined area, it will stay still.
Dirty patches are also specified on input and when the robot hoover moves over a dirty patch, it cleans it.
The program returns a final position and the number of patches cleaned.
Both the input sent to the controller and the output returned are saved to a PostgreSQL database. Details on how to configure the database before running the program are below. 

The user specifies input by sending data as JSON (format shown below) to an endpoint from where the instructions can be processed.

## How to run me

Before running the program, it is necessary to create the database in PostgreSQL. To do this, ensure PostgreSQL is installed - see https://www.postgresql.org/download/ or using homebrew for MacOs run `brew install postgresql`.

Once PostgreSQL is installed, run the following to create and then connect to a new database called 'hoover':

```
$ psql -d postgres
$ CREATE DATABASE hoover;
$ \c hoover;
```

Maven is required to build and run this project. Installation instructions can be found at https://maven.apache.org/install.html.

Once maven is installed, to install all external libraries and dependencies, and to build the project after getting the source code, run `mvn install` in the terminal.

To run the program, type 
`mvn spring-boot:run` 
into the terminal from the home directory of the project.

##How to send instructions to the hoover
 
This can be done via curl or via an application like Postman.

If using curl, an example of how to send instructions is below:

```
curl -X POST -H "Content-Type: application/json" -d @path/to/instructions.json http://localhost:8080/sendInput
```
This assumes that the request is being made to a local server; replace the `http://localhost:8080` with the appropriate base url when the application has been deployed.

Instructions are sent to the hoover via an HTTP POST request to the `/sendInput` endpoint. Instructions are sent as JSON in the following format:


```json
{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```

####Explaining how the program runs 
This would indicate that there is a 5x5 area and that the hoover will start at the square (1,2): two from western boundary and 3 from the southern boundary.

There are patches at coordinates (1,0), (2,2) and (2,3).

The navigation instructions mean that the hoover will pass through the dirty patch at (2,3) after 4 moves.

The hoover will then finish at position (1,3) and will have cleaned one patch.
The output must be returned in the below format (populated with the data that would be generated for the given input):

```json
{
  "coords" : [1, 3],
  "patches" : 1
}
```

## How to run the tests

The tests can be run through maven by inputting the command 
```
mvn clean test
```
