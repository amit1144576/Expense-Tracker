---
layout: post
title: "ISEE 2019: Requirements and System Design Blog"
---

---

# APPLICATION NAME
 
## _**Catch your Mon€y**_

---
# REQUIREMENT ANALYSIS

## _BASIC REQUIRMENTS GIVEN BY THE CUSTOMER_

> **Category**

Every transaction of money must be catgorized, for instance, whether the money is spent on food, living expenses or any other.

Also, with the default catgories the customer will also be able to remove or add a new catgory of his choice.

> **Transaction Method**

The option will be given to the user to select whether the transaction of the money has been done through cash or credit card.


> **Comment Section**

The customer can use the comment section,if there is an additional information that the he wants to save regarding a particular transaction, for his future reference.

the comment section is option and its upto the customer choice.


> **Recurring Transactions**

For recurring transactions, the customer can select a specific date for the recurring payments and the application will automatically make that transaction accordingly.

> **Security**

The option of Validation while logging into the application will be given to the customer, which includes PIN or Password. 

This security procedure is optional and the customer can enable or disable this option according to his prefernce.


> **Transaction Report with filters**

The history of the past transactions can be viewed using different filters, for instance; by date, by transaction method, by transaction type or monthly.


> **Currency Selection**

The customer will be able to select the currency in which he wants to deal.


## _USER STORIES_

For the better understanding and clarification of cutomer's point of view, we came up with several user stories.

![User Stories]({{site.baseurl}}/images/UserStories1.png){:height="150%" width="150%"}

![User Stories]({{site.baseurl}}/images/UserStories2.png){:height="150%" width="150%"}


## _USE CASE DIAGRAM_

To summarize the primary requirements for the application based on User Stories, we used Use Case Diagram,

![View/Modify Records and Settings]({{site.baseurl}}/images/Usecasedaigram.jpg){:height="150%" width="150%"}

In the above diagram we tried to summarize some of the very basic realtionships between use cases, customer and system, which includes
viewing "Reports" and modifying "Settings".


## _ESTIMATED TIME FOR EACH TASK_
 
We distributed our tasks into two main catgories, "Requirement Analysis" and the "System Design"

![Estimated time for the tasks]({{site.baseurl}}/images/esttime_mileston2.png){:height="150%" width="150%"}


# SYSTEM DESIGN

## _CLASS DIAGRAM_

For constructing an executable code and documenting & describing all the different aspects of the application, we created a Class Diagram,

![Class Diagram]({{site.baseurl}}/images/ClassDiagram.png){:height="150%" width="150%"}

## _Details of Attributes and Methods of Class Diagram_

![login]({{site.baseurl}}/images/login and home.png){:height="100%" width="100%"}


![settings]({{site.baseurl}}/images/settings.png){:height="100%" width="100%"}


![reports]({{site.baseurl}}/images/reports.png){:height="100%" width="100%"}


![database]({{site.baseurl}}/images/database.png){:height="100%" width="100%"}


![transaction class]({{site.baseurl}}/images/transaction and base class.png){:height="100%" width="100%"}


## _Activity Diagram(Add Transaction)_

For the better understanding of the dynamic aspects of the appplication, we used two activity diagrams. one for the visulization of
"adding transaction" feature and the other one is for viewing the "previous transaction reports/history".

![Activity diagram of "Add Transaction"]({{site.baseurl}}/images/ActivityDiagram_AddTransaction.png){:height="100%" width="100%"}


## _Activity Diagram(View Report)_

![Activity diagram of "View Report"]({{site.baseurl}}/images/ActivityDiagram_Report.png){:height="100%" width="100%"}

# DEVELOPMENT STRATEGY

To keep up with customer needs, after every meeting with the customer we modify and add new tasks for our next sprint. We mainly use "user stories", to get more clear idea about the requirements of the customer.

For managing and monitoring our tasks and progress, we arrange group meetings every week. In the group meetings we discuss different aspects of the tasks given by customer, ideas and problems related to that task and after that, with mutual understanding we assign the tasks to the group members along with estimated time limit for that particular task.
For further communication and monitoring of the progress we use trello, thats how we keep track of different activities which are in progress and how many of them are done.

![Trello and Group meetings]({{site.baseurl}}/images/trello1.png){:height="100%" width="100%"}

# WORKING PROTOTYPE OF THE APPLICATION

![Login Screen]({{site.baseurl}}/images/Screenshot_20190513_184202_com.example.catchyourmoney.jpg){:height="30%" width="30%"}

Login screen with security for your financial data.

---

![Home Screen]({{site.baseurl}}/images/Screenshot_20190513_184213_com.example.catchyourmoney.jpg){:height="30%" width="30%"}

Home screen with the display of your current balance and buttons for the different functionalities.

---

![Report Screen]({{site.baseurl}}/images/Screenshot_20190513_184227_com.example.catchyourmoney.jpg){:height="30%" width="30%"}

Basic prototype of the "Report" screen with different filters, to view history of the past transactions.

---

![Add Transaction Screen]({{site.baseurl}}/images/Screenshot_20190513_204828_com.example.catchyourmoney.jpg){:height="30%" width="30%"}

Screen to add transaction and its attributes.

---

![Settings Screen]({{site.baseurl}}/images/Screenshot_20190513_212304_com.example.catchyourmoney.jpg){:height="30%" width="30%"}

This screen can be used to modify settings by the user, e.g. add or remove a category.

## Short Video of the Application

<video poster="{{site.baseurl}}/images/Prototype.jpg" width="618" height="347" controls preload>
    <source src="{{site.baseurl}}/images/Prototype.mp4" media="only screen and (min-device-width: 568px)"></source> 
</video>


If you want to test the prototype for yourself, you can find it [here](https://github.com/DBSE-teaching/isee2019-NOOBS.apk/blob/master/Apk%20files/CatchYourMoneyV01.apk).


_**Thats all for now, Thank you for visiting our blog.**_
