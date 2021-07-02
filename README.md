
<h1>Chat Room</h1>

<h3>this is a full-stack app that allows users to share a chatroom together. using Java, Spring MVC and SQL in server side,
and Bootstrap, Thymeleaf, JavaScript and HTML in client side.</h3>

<p>the "Main Controller" handles all URL's. first the user needs to register and log in, where the login status is saved in the session
that is a bean injected into controller, according to the principles of Inversion of control - dependency injection.</p>

<h3>Log in page:</h3>
<p>when a user wants to register, his username is checked against 
the list of all other users that injected from a bean in application scope. errors are displayed to the user via the Thymeleaf library errors handling.</p>

<p>
the session of every user is handled via bean injection (session scope obviously). there is also a listener that is triggered whenever the session 
is destroyed (and then deletes this user from the list of connected user.</p>

<p>an interceptor is registered to make sure that the user is logged in by every new request, and redirects to the home page otherwise.</p>

<h3>Chat page:</h3>

<p>a json of all the last 5 messages is returned from controller (that gets them from the DB, using Java's interface JpaRepository) ,and are inserted (via ajax) into the html file. 
the messages are updated every 10 seconds with a new fetch, that returns a new json only if a new messages exist - this is checked by sending to the controller (via @pathVariable) the id of the last message.</p>

<p>the same thing is with the list of the other users that are connected now to the chat room.</p>

<p>validation on the messages (not empty, etc) is automatically made with the constructions on the @Entity Message</p>
<h3>Search page:</h3>
<p>allows the user to search for a specific message, or for all messages of a user </p>
<p>the same methods as above are used: REST, JSON, AJAX</p>
