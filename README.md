# Rocket Travel Coding Exercise - Backend Software Engineering
## Description
At Rocket Travel, our team loves travelling. However, many places we travel don’t accept our credit cards, and we have to remember to bring dollar bills with us. We’ve had to correct a few vendors about giving us the correct change from their cash registers: sometimes we get too much and sometimes we get too little. Create a cash register that should be able to accept $20, $10, $5, $2 and $1 bills. If there are sufficient bills of the correct denomination, it should be able to return exact change in each denomination from the cash register. If there are insufficient bills for exact change, it should say so.

### Expected Features
Your application should:
Provide implementation for the following features (see examples below):
1. show: Output the current number of each denomination in the register in format $<total> <# of 20’s> <# of 10’s> <# of 5’s> <# of 2’s> <# of 1’s>
2. put: Adds some number of each denomination from the register, then print the current state. Same output format as show command.
3. take: Removes some number of each denomination from the register, then print the current state. Same output format as show command.
4. change: Returns change for some amount of money. Output should be denominations of change for the value asked in format <# of 20’s> <# of 10’s> <# of 5’s> <# of 2’s> <# of 1’s>, e.g. 0 0 4 0 0. This should also deduct the resulting denominations from the register.
5. quit: Exit the program

### Example
> Assume that the cash register was initialized with no bills. put bills in each denomination in: $20's $10's $5's $2's $1's then show the current state

``` put 1 2 3 4 5 ```

``` $68 1 2 3 4 5 ```

> Show the current state of the cash register with the total and each denomination. $Total $20's $10's $5's $2's $1's Total=$68 $20x1 $10x2 $5x3 $2x4 $1x5

``` show ```

``` $68 1 2 3 4 5 ```

> Put bills in each denomination in: $20's $10's $5's $2's $1's * then show the current state.

``` put 1 2 3 0 5 ```

``` $128 2 4 6 4 10 ```

> Take bills in each denomination out: $20's $10's $5's $2's $1's * then show the current state

``` take 1 4 3 0 10 ```

``` $43 1 0 3 4 0 ```

> Return change for a given amount by showing the number of each denomination the vendor needs to return: $20's $10's $5's $2's $1's * and remove money from the cash register

``` change 11 ```

``` 0 0 1 3 0 ```

> If there is not enough funds in the register or no change can be made, show an error

``` change 14 ```

``` sorry ```

> Exit the program

``` quit ```

``` Bye ```

## Instruction
### Requirements
1. JDK 1.8 or later
1. Gradle 4+ or Maven 3.2+
1. A favorite text editor or IDE

### Steps

#### IDE
1. Clone the repository and import the folder into your editor.
2. Clean and build the project.
3. Start the services by running App application

### Terminal
1. cd to your project root folder in command line
2. mvn compile
3. mvn exec:java -Dexec.mainClass=com.rockettravel.app.App

