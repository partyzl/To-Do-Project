fetch('http://localhost:9092/todo/read')
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
     
        let table = document.querySelector("table");
        let data2 = Object.keys(data[0]);

        createTableHead(table,data2)
        createTableBody(table,data)

      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

function createTableHead(table,data){
    let tableHead = table.createTHead();
    let row = tableHead.insertRow();
    for(let val of data){
        let th = document.createElement("th");
        let text = document.createTextNode(val);
        th.appendChild(text);
        row.appendChild(th);
    }
    // let th2 = document.createElement("th")
    // let text2 = document.createTextNode("Delete");
    // th2.appendChild(text2);
    // row.appendChild(th2)
}

function createTableBody (table,data){
    for(let record of data){
      console.log(data);
        let row = table.insertRow();
        for(let prop in record){
            let cell = row.insertCell();
            let text = document.createTextNode(record[prop])
            cell.appendChild(text);
        }
        let newCell = row.insertCell();
        let delBtn = document.createElement("button");
        let btnVal = document.createTextNode("X")
        delBtn.className ="btn btn-outline-danger";
        delBtn.appendChild(btnVal);
        newCell.appendChild(delBtn);
        delBtn.onclick = function(){
          deleteToDo(record.id);
          return false;
        }
        
      }
    }
    
function deleteToDo(id){
  fetch("http://localhost:9092/todo/delete/"+id, {
    method: 'delete',
    headers: {
      "Content-type": "application/json"
    },
  })

  .then(function (data) {
    console.log('Request succeeded with JSON response', data);
})
}