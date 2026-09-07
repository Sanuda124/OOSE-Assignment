# Work Breakdown Structure Effort Estimator

## 1. Overview

This Project is a java application for loading , displaying and estimating the effort of tasks in a WBS. The program loads a WBS from a file supplied as a command line argument. It displays the task hierarchy and the current effort infromation. Tasks without and effort estimates can be estimated using a selected reconcilation strategy. The updated WBS is saved back to the same file when the user exists.The application uses the Strategy Pattern for reconciling mutiple effort estimates and the composite pattern for represnting the hierarchical WBS.

## 2. Running the program

The project uses Java and Gradle.

From the project directory, run ,

./gradlew run --args="test.txt"

To run the tests and Pmd checks,

./gradlew clean check

## 3. WBS file Format

The WBS file uses semicolons (';') to seperate fields.
Each line contains either threee or four fields,

paret ID:current ID;description
paret ID:current ID;description;effort

for example, 

;1;My Project
1;2;Website
2;3;Home Page
2;4;Login Page;8
1;5;Database
5;6;Database Setup;10

The first feild is the parent task ID. A blank parent ID means that the task is a root task. The 2nd field is the task's own ID and the 3rd fields is its description.The optional 4th field contains the effort for a lead task.Effort must be a positive integer.A task with children is treated as a group and does not have an effort value in the file. A lead task may have a known effort or may intially have no effort value.White space around fields is removed when the file is loaded.

## 4 . Program Menu

The program provides 3 menu options,

1. estimate effort
2. configure
3. quit

Estimate effort allows the user to select a task ID. unknown leaf tasks below that task are estimated.Configure allows the user to change the number of estimators and select the reconciliation strategy.Quit saves the current WBS to the original input file and exits the program.

## 5.Effort Estimation

The default number of estimators is 3 and the default reconciliation strategy is Discuss.When a n unknown lead task is selected for estimation the program asks for the required number of seperate effort estimates and displays all of the estimates.If all estimates are the same , that value is used directly. If the estimates are different, the selected reconciliation startegy is used.

- Highest selects the highest estimate.
- Median selects the median value. For an even number of estimates, the average of the two middle values is used.
- Discuss asks user to provide the final estimate.

After an estimate is chosen it is stored in the leaf task. Group effort is calculated from the effort of its child tasks.

## 6. Strategy Pattern

The startegy pattern is used for choosing how different effort estimates are reconciled.The EstimateRule interface defines the common method used to choose a final estimate.There are 3 stategy classes,

- HighestRule
- MedianRule
- TalkRule

The estimate class uses and EstimateRule object and calls its choose() method.This means that the Estimate class does not need to know the details of how each reconsiliation method works. The Strategy pattern is useful beacuse the reconciliation method can be changed though the configure option. The main estimation code does not need to be changed when a different rule is selected.An alternative would have been to use one class containing if or switch for Highest , Median and Discuss.

## 7.Composite Pattern

The Composite pattern is used to represent the wbs hierarchy. The Task class is common type for both groups and leaf tasks. Group represensts atask that can cpntain other task while Item represn a leaf task that does not contain other task.A Group contain a list of Task object Since the lists uses the common Task type agroup can contain both other groups and items. This allows nested WBS structures.The getEffort() method is polymorphic. A Item returns its own effort  while a group calcuylates its effort by calling getEffort() on each of its children and adding the results. So the same Task operation can be used for both groups and items.This pattern is useful because WBS naturally has a tree structure.

## 8. Map Usage

The Wbs clas uses a Map<String, Task> to store tasks using thier  task ID as the key.This allows the program to find a task quickly when the user enters a task ID. The ID is unique in the WBS, sp its suitable as the map key.The Wbs class also stores the root tasks in a list. The map is mainly used for finding tasks by ID while the root is used to display  and traverse the WBS hierarchy.

## 9. Error Handling and Logging

The program checks for invalid Wbs input when loading the file.The custom InvalidException is used when the WBs contains invalid data.Example include duplicate task IDs, missing parent IDs, invalid efforts values, missing IDs and incorrect number of fields.The program also checks user input for menu and configuration choices. Invalid menu choices are rejected and the user is asked to enter a valid value.File loading uses IOException to handle problem with reading the WBS file.Saving also uses IOEception.The Filedata class uses java.util.logging.Logger to record information abour loading the Wbs file.

## 10.Testing

JUnit tests are included for umportant parts of the program.

The test cpver the estimation stratefies inlcluding,

- Highest Estimation
- Median Estimation
- Discuss Estimation

The composite structure is also tested including groups items and nested groups.

The project can be check using,

    ./gradlew clean check

This runs the tests and PMD checks.

## 11. Project Structure

The main classes in the project are:

App.java
Task.java
Group.java
Item.java
Wbs.java

EstimateRule.java
HighestRule.java
MedianRule.java
TalkRule.java
Estimate.java
EstimateManager.java

Filedata.java
InvaludWbsException.java

Settings.java
Screen.java
Menu.java
Configure.java

The main resposnsibilities are seperated between these classes.

'App' starts the program and connects the main components
'Task', 'Group' and 'Item' represent the WBS structure
'Wbs' stores and accesses the tasks
'Filedata' loads and saves the WBS file
'EstimateRule' and its implementations provide the different estimation strategies.
'Estimate' Collects esttimates and applies the selected strategy
'EstimateManager' performs estimation on the selected task and its desendants
'Settings' stores the current estimation configuration
'Screen' Displays the WBS and effort information
'Menu' handles the main menu
'Configure' handle changes the estimation settings
'InvalidWbsException' provides a specific exception for invalid WBS data

