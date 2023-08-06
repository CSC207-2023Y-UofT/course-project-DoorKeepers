# Expenditure Tracker

## Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Abbreviations](#abbreviations)
- [Usage](#usage)
  - [Installation](#installation)
- [Clean Architecture Specifications](#clean-architecture-specifications)
  - [Clean Architecture Violations](#clean-architecture-violations)
  - [SOLID Principles](#solid-principles)
  - [Design Patterns](#design-patterns)
- [Authors](#authors)

## Overview

The Expenditure Tracker is a Java application designed to help users manage their expenses throughout a given month. 
This intuitive program offers an array of features that allow users to track, organize, and evaluate their expenditures,
while also providing insightful data analysis for informed financial decision-making.

## Key Features

- **Expense Creation and Editing:** Users can submit and edit numerous expense entries, ensuring an accurate 
representation of their spending patterns.

- **Categorization:** This program enables users to efficiently categorize their expenses, resulting in a well-organized
financial overview. Moreover, users customize their categories to allow for precise sorting of expenses according to 
individual spending habits.

- **Budget Allocation:** Users set a predetermined budget for the month, enhancing financial discipline. Additionally, 
budgets are created per category, allowing for stricter financial management based on user preference.

- **Month Menu:** A user-friendly menu where all expense tracking is facilitated. The month menu is the hub for 
expense and category creation, viewing previous expense logs, and generating the graphical summary. 

- **Graphical Monthly Reports:** A comprehensive graphical representation of the user's spending distribution. 
Generating easy-to-understand graphs through UI frameworks, users gain a visual insight into their financial allocation 
across different categories.

- **Persistent Data Storage:** This program ensures continuity by storing user data onto their local file system, 
allowing access and maintenance across various sessions. This feature guarantees that the financial history and insights
accumulated remain preserved and accessible over time.

## Usage

### Installation

1. Clone the repository: `git clone https://github.com/CSC207-2023Y-UofT/course-project-DoorKeepers.git`
2. Locate local file in your terminal or open in an IDE.
3. Navigate to the correct directory: `cd src/main/java`
4. Compile the project: `javac Main.java`
5. Run the project: `java Main`

## Abbreviations

| Name              | Abbreviation |
|-------------------|--------------|
| Controller        | C            |
| UseCaseInteractor | UCI          |
| InputBoundary     | IB           |
| InputData         | ID           |
| OutputBoundary    | OB           |
| OutputData        | OD           |
| Presenter         | P            |
| ViewBoundary      | VB           |
| View              | V            |
| Gateway           | G            |


## Clean Architecture Specifications

### Clean Architecture Violations
- Expense and Category are basic object types we have for conveying information that are not separable. For instance, an 
expense called "Walmart" would have no meaning unless we know the specific amount of money spent shopping in Walmart. 
These classes belong to the Entity layer, but are passed around in both the use_case and views packages, which is a 
Clean Architecture violation. We could remove the imports to these entities, and change the output data to Object[][] 
type in the UpdateViewUCI to accommodate Java Swing requirements. However, we thought it is not adhering to SOLID 
principle from the point that Object[][] may not be the type required by other view libraries that could be imported, 
which is in contrary to the Open/Closed Principle.
- SessionStorage is an object type used to hold all information contained in the user's session. Every time a use case 
is called, the interactor of that use case needs to access the information within the SessionStorage object. Thus, it 
needs to be passed throughout all layers of our program, despite being a violation of Clean Architecture.

### SOLID Principles
- **Single Responsibility Principle:** All classes are acted upon by a single actor and adhere to this principle.
- **Open-Closed Principle:** Since our classes with main functionality implement IB and OB interfaces, the user is able 
to modify any interactor or presenter as they see fit. For example, if the user wanted to implement another view that 
generates statistical data with all months they could create another interactor to do that.
- **Liskov Substitution Principle:** All of our IB and OB objects adhere to this principle. In order to adhere to the 
DIP, the interactors and presenters are cast to the IB and OB, respectively. This demonstrates the LSP, as any class 
that implements these interfaces can be used. Another example is the LoadMonthMenuVB, which 
- **Interface Segregation Principle:** All of our interfaces adhere to this rule, as they are all small and contain only
the necessary methods needed.
- **Dependency Inversion Principle:** All classes have a boundary between every layer in Clean Architecture, to create 
the dependency inversion.

### Design Patterns
- The Facade design pattern is used to implement the GenerateSummaryUCI to delegate tasks to the 
GenerateSummaryUCInterpreter for generating the statistical data, and the GenerateSummaryP for returning it.
- In the future, we could implement the Factory design pattern to implement the Add/Edit Categories and Add/Edit 
Expenses.

## Authors

This project was created by Ari, Katarina, Lulu, and Yin for [CSC207] Software Design at the University of Toronto.