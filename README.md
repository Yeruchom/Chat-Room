chat room

this is a fullstack app that allows the users to share a chatroom together.
using Java, Spring MVC and SQL in server side, and Bootstrap, Thymeleaf, JavaScript and HTML in client side.

the "Main Controller" handles all URL's. first the user needs to register and log in, where the login status is saved in the session
that is a bean injected into controller, according to the principles of Inversion of control - dependency injection.

we checked that the users name does not exist yet, and if it does we display a message to enter another name.

as written in the instructions, the login page is handled with Thymeleaf, including error messages when the input is not valid etc.

the session of every user is handled via bean injection (session scope obviously). there is also a listener that is triggered whenever the session is destroyed (and then deletes this user from the list of connected user (this list is in application scope, also handled via bean injection).

also we have an interceptor that makes sure every time that the user is logged in, and redirects to the home page otherwise.

the list of connected user is returned as json from the controller, and with ajax is inserted into the html file. the same thing is with the messages (tha last 5) that we get from the db with fetch to the url/controller. and the same also by the search that returns messages.

every 10 seconds there is a new fetch to get updated messages and connected user. we send to the controller (@pathVariable) the id of the last message, int if there is no new message the controller returns null, so we know not to update the html for nothing. the same is about the list of connected user.
