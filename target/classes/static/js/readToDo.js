let id = prompt("Give me a value between 1-100");
let newID = parseInt(id);
getOneToDo(newID);
// const params = new URLSearchParams(window.location.search);

// for(let param of params ){
//     console.log(param)
//     let id = param[1];
//     console.log(id);
//     getOneToDo(id)
// }
function getOneToDo(){
    // let newID = parseInt(id);
fetch('http://localhost:9092/todo/read/'+ newID)
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }

      // Examine the text in the response
      response.json().then(function(data) {
        console.log(data);

        document.getElementById("idInput").value=data.id
        document.getElementById("nameInput").value=data.todoTitle
      


      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
}
// function createTableHead(table,data2){
//     let tableHead = table.createTHead();
//     let row = tableHead.insertRow();
//     for(let val of data2){
//         let th = document.createElement("th");
//         let text = document.createTextNode(val);
//         th.appendChild(text);
//         row.appendChild(th);1
// }

// function createTableBody (table,data){
//     // for(let record of data){
//         let row = table.insertRow();
//         for(let prop in data){
//             let cell = row.insertCell();
//             let text = document.createTextNode(data[prop])
//             cell.appendChild(text);
//         }
//     }
document.querySelector("form.toDoForm").addEventListener("submit",function(stop){
  stop.preventDefault();
  
  let formElements = document.querySelector("form.toDoForm").elements;
  let toDoId = formElements["idInput"].value;
  let toDoName = formElements["nameInput"].value;
  updateToDo(toDoName,toDoId)
})
function updateToDo(toDoName,toDoId){

  let updateToDoTitle = toDoName;
  

  let dataToPost ={
      
          "todoTitle": updateToDoTitle
  }

  fetch("http://localhost:9092/todo/update/"+toDoId, {
      method: 'put',
      headers: {
        "Content-type": "application/json"
      },
      body:JSON.stringify(dataToPost)
    })
 
    .then(function (data) {
      console.log('Request succeeded with JSON response', data);
    })
    .catch(function (error) {
      console.log('Request failed', error);
    });
}


