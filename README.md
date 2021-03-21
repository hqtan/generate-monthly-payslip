## Coding Exercise: Generate Monthly Payslip

This is a small console application written in Scala that outputs an employee's monthly pay slip, given an employee's name and annual salary.

```
~/generate-monthly-payslip$ ./GenerateMonthlyPayslip 

Usage: GenerateMonthlyPayslip <NAME> <SALARY AS INTEGER VALUE>

example: GenerateMonthlyPayslip "Mary Song" 60000

 This script outputs a monthly pay slip given an employee name and annual salary. 

```

### Requirements:
- The console app needs `docker` service running on local host
- an internet connection to download required docker image (see [Dockerfile](Dockerfile)).

### Instructions: Running console app
1. unpack compressed file
2. `cd generate-monthly-payslip`
3. `./GenerateMonthlyPayslip`

Upon the first time running `./GenerateMonthlyPayslip` script, it will build a docker image containing the app. This may take a while depending on internet connection.

### Assumptions/Limitations:
I assume:
- this app won't be running 'heavy' workloads (it hasn't been optimised and isn't currently very performant)
- the tax rates doesn't need to be easily 'configurable' by a non-technical person that doesn't have software development experience
- the annual salary (provided as an input) is only given as a positive integer value, with no decimal values supplied
- the supplied employee name doesn't need to be checked for certain naming conventions
- the app currently prints the '$' sign as a '¤' character. I suspect this is due to the docker image I've chosen to use for this exercise, but haven't had the time to investigate due to time constraints
- I haven't used a code linter for my codebase, as the codebase is currently small enough to be understandable, and I assume no one else but me will be making changes to it

### Running Test Suite
run `./run-tests` script

### Rebuilding the app after making changes to code
1. ```docker build -t gen-monthly-payslip .```
2. `./GenerateMonthlyPayslip`
