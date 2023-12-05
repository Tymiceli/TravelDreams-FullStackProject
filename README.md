# TravelDreams-FullStackProject

This is a full stack CRUD application that consumes REST Countries API to gather data about all of the countries in the world. 

If you have dreams of travelling all over the world but need a way to organize those dreams, then this app is for you!

# **_[Start Dreaming!](https://traveldreams-fullstackproject-production.up.railway.app/home)_**

Technologies Used:
- Front End:
    - HTML5
    - CSS
    - ThymeLeaf
    - Spring MVC
    - Bootstrap
- Back End:
    - Java
    - Hibernate
    - Spring
    - Maven
- Database:
    - MySQL
    - Hibernate ORM

# Getting started:
## If you would like to start your own instance of TravelDreams, here is what you need to do:
- clone my github repository
- import as a Maven project
- you will need to create your own instance of a database, I used MySQL workbench to do this
- update the application.propertiesFIXME file, rename it to application.properties
- wherever you see "FIXTHIS", you will need to use your own data, the link to your database, your username and pass, etc.
- for the JWT signingKey, you will need a 64-bit random hexadecimal character array, I recommend easily generating one from this website (https://www.grc.com/passwords.htm)
- set your own expiration times for the JWTs and the refresh tokens
- you should then be able to run your app and navigate to the login page when typing http://localhost:8080/login
- register with your first, last, username, email, and password
- you may now login with that username and password
- to demonstrate one of Spring Security's authorization abilities I have made the method for populating the database with the countries from the api only possible once you navivate to the endpoint "http://localhost:8080/get-all-countries" and have the role of admin set to your user.
- to do this, simply insert into the authorities table in the database with this command: 
    - INSERT INTO authorities (`user_id`,`authority`) VALUES (1,'ROLE_ADMIN');
- After this, your database will be populated with the countries and you will be able to view them all, add them to your list of countries you are dreaming of travelling to, remove them from the list, update your account, etc.
## more features coming soon...