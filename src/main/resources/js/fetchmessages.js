


    document.addEventListener('DOMContentLoaded', function () {
        // console.log("in html");
        FetchMessages();
        FetchConnected();
        setInterval(FetchMessages, 10*1000);
        setInterval(FetchConnected, 10*1000);
    }, false);

    function FetchMessages() {
        let lastId = document.getElementById("data").innerHTML;
        lastId = (lastId != undefined && lastId > 0) ? lastId : 0;

        let url = new URL("/chatroom/getChat/"+ lastId, window.location.href);
        fetch(url).then(
            function (response) {
                // handle the error
                if (response.status !== 200) {
                    console.log("there was an error. response.status: ", response.status);
                    return;
                }
                response.text().then((text)=> {
                    if (text.length > 0)
                        DisplayChat(JSON.parse(text));
                    else {
                        let chat = document.getElementById("chat");
                        if(chat.childElementCount == 0) {
                            let noUser = document.createElement("li");
                            noUser.className = "list-group-item list-group-item-info";
                            noUser.innerHTML = "there are no messages yet";
                            chat.appendChild(noUser);
                        }
                    };
                })
            })
            .catch(function (err) {
                console.log('There was an error fetching ' + url +'. error: '+  err);})
    }

    function DisplayChat(chatMessages){

        let chatWindow = document.getElementById("chat");

        // first clear all messages
        while (chatWindow.hasChildNodes())
            chatWindow.removeChild(chatWindow.lastChild);

        // display the messages
        for(var i in chatMessages){
            let m = document.createElement("li");
            m.className = "list-group-item list-group-item-info";
            m.innerHTML = "name: " + chatMessages[i].name + " " + chatMessages[i].text;
            chatWindow.appendChild(m);
        }
        if(i !== undefined)
        {
            document.getElementById("data").innerHTML = chatMessages[i].id;
            // console.log("i:", i, " id ", document.getElementById("data").innerHTML);
        }
    }

    function FetchConnected() {

        let numberOfConnected = document.getElementById("numberOfConnected").innerHTML;
        numberOfConnected = (numberOfConnected != undefined && numberOfConnected > 0) ? numberOfConnected : 0;
        let url = new URL("/connectedUsers/" + numberOfConnected, window.location.href);

        fetch(url).then(
            function (response) {
                // handle the error
                if (response.status !== 200) {
                    console.log("there was an error. response.status: ", response.status);
                    return;
                }
                response.text().then((text)=> {
                    if (text.length > 0)
                        DisplayConnected(JSON.parse(text));
                    else
                    {
                        let con = document.getElementById("connected");
                        if(con.childElementCount == 0){
                            let noUser = document.createElement("li");
                            noUser.className = "list-group-item list-group-item-info";
                            noUser.innerHTML = "No other user is connected";
                            con.appendChild(noUser);
                        }

                        return;
                    }
                })
                // response.json().then(DisplayConnected);
            })
            .catch(function (err) {
                console.log('There was an error fetching ' + url +'. error: '+  err);})
    }

    function DisplayConnected(users){

        let connectedWindow = document.getElementById("connected");
        // first clear all messages
        while (connectedWindow.hasChildNodes())
            connectedWindow.removeChild(connectedWindow.lastChild);

        // display the messages
        for(i in users){
            let m = document.createElement("li");
            m.className = "list-group-item list-group-item-info";
            m.innerHTML = i.toString() + " - " + users[i] + " is connected ";
            connectedWindow.appendChild(m);
        }
        if(i !== undefined)
        {
            document.getElementById("numberOfConnected").innerHTML = i;
            // console.log("i:", i, " id ", document.getElementById("data").innerHTML);
        }
    };
