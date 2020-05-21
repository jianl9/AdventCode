## Advent Of Code Day 1, 4, 10



## Environment

1. Maven
2. Java 8
3. Junit 4



## Run
under *AdventDay* folder, issue following command in terminal to compile and test   

`mvn clean install` 

and then run by issuing

`mvn exec:java -Dexec.mainClass="Package.class"`

For day 1, `mvn exec:java -Dexec.mainClass="AdventDay1.GetCityDistance"`

For day 4, `mvn exec:java -Dexec.mainClass="AdventDay4.SecurityThroughObscurity"`

For day 10, `mvn exec:java -Dexec.mainClass="AdventDay10.BalanceBots"`



## Project Tree

```.
├── AdventDay.iml
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   ├── AdventDay1
    │   │   │   └── GetCityDistance.java
    │   │   ├── AdventDay10
    │   │   │   └── BalanceBots.java
    │   │   └── AdventDay4
    │   │       └── SecurityThroughObscurity.java
    │   └── resources
    │       ├── day10Input.txt
    │       ├── day1Input.txt
    │       ├── day1InputMock.txt
    │       └── day4Input.txt
    └── test
        └── java
            ├── AdventDay1
            │   └── GetCityDistanceTest.java
            ├── AdventDay10
            │   └── BalanceBotsTest.java
            └── AdventDay4
                └── SecurityThroughObscurityTest.java
```



## Answer

### Day 1

#### Exercise Part 1:

How many blocks away is Easter Bunny HQ?

Your puzzle answer was `246`.

#### Exercise Part 2:
How many blocks away is the first location you visit twice?

Your puzzle answer was `124`.



### Day 4

#### Exercise Part 1:

What is the sum of the sector IDs of the real rooms?

Your puzzle answer was `361724`.

#### Exercise Part 2:

What is the sector ID of the room where North Pole objects are stored?

Your puzzle answer was `482`.



### Day 10

#### Exercise Part 1:

what is the number of the bot that is responsible for comparing value-61 microchips with value-17 microchips?

Your puzzle answer was `118`.

#### Exercise Part 2:

What do you get if you multiply together the values of one chip in each of outputs 0, 1, and 2?

Your puzzle answer was `143153`.
