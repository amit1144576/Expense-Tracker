---
layout: post
title: "ISEE 2019: Advanced Prototype"
---
Welcome to our 3rd blog entry of this project. In this entry we will inform you about our progress in programming our advanced prototype of our app *Catch Your Mon€y*

# CHANGES IN REQUIREMENTS
## _**New Requirements**_

For the advanced prototype we recieved new requirements from the customer. Most of them were related to already existing functionalities of the app, but some were completely new.

* The report screen should contain at least two differnt kind of charts which display the report of the past transaction in a graphical manner.
* The categories for transactions should have icons for better identification. This includes that the user can use an icon out of a libary for his created categories.
* Already inserted transaction should be deletable.
* The user should be able to set a monthly budget.
* A possibility to send a monthly report via e-mail should be integrated

---
## _**Changes in User Stories**_
After some discussion with the customer we came up with new user stories for the above mentioned requirements. In the first picture you can see in the top row the old user stories and below the related new ones.

![Changes in User Stories]({{site.baseurl}}/images/ChangesUserStories.PNG){:height="120%" width="120%"}

Some of the requirements led to totally new user stories which are shown in the following picture.

![Changes in User Stories]({{site.baseurl}}/images/NewUserStories.PNG){:height="120%" width="120%"}

---
# CHANGES IN STRUCTURE
The new requirements led of course also to changes in the structure of the application. The main core stayed the same but the report page needed some adjustments. As you can see in the following UML diagram we decided to make sub pages for the report which are inherited of the super report class. In the subclasses we then adjusted the behaviour of the class to be align with the information we want to show in the different graphs. Of course there are a lot of different approaches how this functionality can be implemented and one different one will be discussed in the next chapter.

![Changes in the Structure]({{site.baseurl}}/images/Class Diagram Update.png){:height="1%" width="120%"}

---

# DESIGN PATTERN
The problem with the report screen could also be solved with the so called *Decorator* design pattern, shown in the picture.
The idea behind this pattern is that you create a decorater class for your basic class that adds functionalities to the basic class in a flexible way. The huge advantage of this method is the flexibility which means you can adjust your class behaviour during runtime. To achieve this you can wrap your basic class (Report) with different decorator classes (like Overview, PieChart or BarChart) and these will change the behaviour of the basic class accordingly. This leads to high flexibility because you could implement in the settings that the user could enable or disable different charts or something similar. Since for the current requirements we don't need this much flexibility we decided to not implement this design pattern.

![Decorator]({{site.baseurl}}/images/Decorator.png){:height="150%" width="150%"}

---
# CODING CONVENTIONS
During our projects we try as good as possible two follow the JavaSoft coding conventions to make our lifes easier. These include some basic regulations about naming, like:
* Class: Name should be a noun with the first letter capitalized
* Method: Name starts with a verb and with lower case
* Variables: Names should be descriptive, not to long and start with lower case
* Constants: Same as variables but their names should be fully capitalized

On top of the basic conventions we also agreed on our own rules. These mainly revolve around the usage of the same vocabulary for the same things, like everything regarding the login should be named *authentication* and to establish standard abbreviations (e.g tv for text view or spin for spinner).

Last but not least we tried to make as much comments in the code as are needed for the other developers to understand the code even if this was sometimes very laborious.

To ensure that everybody follows the conventions the other team mates reviewed the code and pointed out potential deviations.

---
# CONTEXT OF USE
To get a better understanding what our potential app users might expect from this application we came up with two personas that illustrate the goals and backgrounds of our target group.

![Personas]({{site.baseurl}}/images/Personas.PNG){:height="120%" width="120%"}

As shown in the picture Saira is a quite demanding user who wants to personalize her apps and dive into the different options wheras Jan just wants to use the basic functionalites. These two personas reflect the two extremes of our user group which means we have to make an app that is easy to use but also has some adjustability to it.

---
# UI DESIGN
## _**Design Principals**_
For the design of our UI we followed three basic design principals:
* Usability
* Understandability
* Minimalistic
With these principals we want to ensure that our design is mainly driven by the function and is not distracting the user from their goal. The functionality should also be clear to the user on first glance and the design should look modern and clean due to the minimalistic approach.

---
## _**Color Scheme**_
The color palette of our app mainly consists of green accents and a white background. We chose green because it is associated with growth, stability and wealth which fits perfectly with the financial topic of the app.

For the background we decided for plain white to support the modern, minimalistic look.

---
## _**Story Board: Adding Transaction**_
In the following picture a series of screenshots is shown which resembles the task of inserting a new transaction. In the first screenshot the user is at the *Home* page and clicks on the left button to add a new transaction. The next step shows the *Add Transaction* screen where the user inserts the details of his/her transaction. In this example the new transaction is an expense of the category wine and was payed on the 1st June 2019. If the user clicks on *Add Transaction* and is going back to the home screen he/she will see the updated balance there.

![Story Board: Adding Transaction]({{site.baseurl}}/images/StoryboardTransaction.PNG){:height="120%" width="120%"}

---

## _**Story Board: Activating Login**_
The next story board shows the user story of activating the login for the app. The user clicks on the right button in the *Home* screen to get to the *Settings* screen. There he enables the login by clicking on the corresponding switch. After that a pop up will show up we he/she needs to insert the password. If the user now restarts the app he/she now needs to insert the password before accessing the *Home* screen.

![Story Board: Activating Login]({{site.baseurl}}/images/StoryboardLogin.PNG){:height="120%" width="120%"}

---
That is all for now. If you want to check out the current version of the app you can download it [here](https://github.com/DBSE-teaching/isee2019-NOOBS.apk/blob/master/Apk%20files/CatchYourMoneyV02.apk).

_**Thanks for visting our blog!**_
