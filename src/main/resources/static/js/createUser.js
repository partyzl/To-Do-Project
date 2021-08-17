// const params = new URLSearchParams(window.location.search);
    
// for(let param of params ){
// let id = param[1];
// console.log(id);
// getData(id)

// function getToDo(id){
    document.querySelector("form.createUser").addEventListener("submit", function(stop){
        stop.preventDefault();
    let formElements = document.querySelector("form.createUser").elements;

    
    
    let name = formElements["nameInput"].value;
    let id = formElements["toDoID"].value;
    let age = formElements["ageInput"].value;
    let name2 = formElements["toDoInput"].value;
    console.log(name,age, id, name2);
    newUser(name, age,id, name2);
    
    })
    
    function newUser(name, age, id, name2){
    
    
    let dataToPost ={
            
            "name": name,
            "age": age,
            "toDos": [
                {
                    "id": id,
                    "todoTitle": name2 
                    
                }
            ]
            
        }
            // create(data)
            window.location.replace("UserHome.html");
    
    fetch("http://localhost:9092/user/create", {
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
    
    