chat room
by Yeruchom Heller 

link to javadoc
first of all, we checked that the users name does not exist yet, and if it does we display a message to enter another name.

as written in the instructions, the login page is handled with Thymeleaf, including error messages when the input is not valid etc.

the session of every user is handled via bean injection (session scope obviously). there is also a listener that is triggered whenever the session is destroyed (and then deletes this user from the list of connected user (this list is in application scope, also handled via bean injection).

also we have an interceptor that makes sure every time that the user is logged in, and redirects to the home page otherwise.

the list of connected user is returned as json from the controller, and with ajax is inserted into the html file. the same thing is with the messages (tha last 5) that we get from the db with fetch to the url/controller. and the same also by the search that returns messages.

every 10 seconds there is a new fetch to get updated messages and connected user. we send to the controller (@pathVariable) the id of the last message, int if there is no new message the controller returns null, so we know not to update the html for nothing. the same is about the list of connected user.
