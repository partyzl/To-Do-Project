// const params = new URLSearchParams(window.location.search);
    
// for(let param of params ){
// let id = param[1];
// console.log(id);
// getData(id)

// function getToDo(id){
document.querySelector("form.createToDo").addEventListener("submit", function(stop){
    stop.preventDefault();
let formElements = document.querySelector("form.createToDo").elements;


let name = formElements["nameInput"].value;
console.log(name);
newToDo(name);

})

function newToDo(name){


let dataToPost ={
        
        "todoTitle": name
        
    }
        // create(data)
        window.location.replace("ToDoHome.html");

fetch("http://localhost:9092/todo/create", {
    method: 'Post',
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

