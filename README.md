CoolPay App

1. Introduction

The purpose of this app is provide an user interface in order to send money for “recipients” previously registered on the system.
In order to develop the app, the following technologies were used to:
•	Eclipse for JEE developers version Oxygen Release (4.7.0), and JBoss Tools installed.
•	Java EE7 with CDI
•	Application Server Wildfly 10.1 embedded within Eclipse.
•	Maven integration with Eclipse.

2. Configuration

The system can be addressed to “consume” the three possible environments, through the web.xml context parameter called “ServerEnvironment”, and the possible options are: “MockEnv”, “DebuggingProxyEnv” and “ProductionEnv”.
The system is configured to run on the MockEnv. The currency available is “GBP”. If will be necessary provide more currencies, this could be carried out through the class “CurrencyType”, simply adding a new currency.
The system is documented through “Javadoc”.

3. Requirements

The customer asks for three features:
1.	Authentication, which returns a token in order to provide authorization for the further transactions with the server.
2.	Recipients registration and search, which will be done through the “Recipients” link, which is visible to the user after the authentication, and also a searching in order to verify whether a “recipient” is registered or not. In order to verify a possible already registered, the form “list” for the Recipients has a “text box” inside the data grid, which provides a dynamic filter. If the user couldn’t see the recipient there, it may be no registered already on the system.
3.	Payments registration and status verification: in order to carry on these tasks, the user can proceed a new payment through the link “Send Money” within the data grid, which is available for each recipient already registered on the system. In order to verify the payments which are already made for each recipient, the user can use the link “All Payments” also inside the data grid at the “list” screen for the recipients. Also the system validate the name of the recipient while we are registering a new recipient, through the user interface validation.

4. Deploy

The deploy can be made for a WildFly 10.1 server or any other JEE7 Container. But the application was only tested running with Wildfly 10.1
