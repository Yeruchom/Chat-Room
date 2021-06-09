(function () {

//     //this function handles the show button.
//     // (makes a fetch request to the servlet)
//     var showHandler = function (event) {
//         if (event.target.name == "show") {
//             var q = event.target.id;
//
//             let url = new URL("/AddAnswersServlet?q=" + q.toString(), window.location.href);
//
//             //get the answers
//             fetch(url).then(
//                 function (response) {
//                     // handle the error
//                     if (response.status !== 200) {
//                         console.log("there was an error. response.status: ", response.status);
//                         return;
//                     }
//                     response.json().then(showAnswers);
//                 }
//             )
//                 .catch(function (err) {
//                     console.log('There was an error getting the required answers :', err);
//                 }).catch((err) => {
//                 console.log('Fetch Error :', err);
//             })
//         }
//     }
//
//
// //this function adds all answers from the json object to the html document
//     function showAnswers(json) {
//
//         document.getElementById("ans").style.display = "block";
//         let tbody = document.getElementById("ans").childNodes[1].lastChild;
//
//         while (tbody.hasChildNodes())//delete all old answers
//             tbody.removeChild(tbody.lastChild);
//
//         for (i in json) {
//
//             let line = document.createElement("tr");
//             line.appendChild(document.createElement("td"))
//             line.lastChild.innerHTML = parseInt(i) + 1;
//             line.appendChild(document.createElement("td"))
//             line.lastChild.innerHTML = json[i]["name"];
//             line.appendChild(document.createElement("td"))
//             line.lastChild.innerHTML = json[i]["answer"];
//             tbody.appendChild(line);
//         }
//     }
//
//
// //a function that handles the hide button
//     function hideAnswers() {
//         document.getElementById("ans").style.display = "none"
//     }

    console.log("in js 1");

    document.addEventListener('DOMContentLoaded', function () {
        console.log("in js");
        // document.getElementById("container").addEventListener("click", showHandler);
        // document.getElementById("hide").addEventListener("click", hideAnswers);
    }, false);
}());