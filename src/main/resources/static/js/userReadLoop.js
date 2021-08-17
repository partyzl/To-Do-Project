  
// fetch('http://localhost:9092/user/read')
//   .then(
//     function(response) {
//       if (response.status !== 200) {
//         console.log('Looks like there was a problem. Status Code: ' +
//           response.status);
//         return;
//       }

//     response.json().then(function(data) {
//           console.log(data)
//         for(let toDo of data){
//             console.log("To Dos assigned to users",toDo.toDos);
//             for(let oneToDo in toDo.toDos){
//                 console.log("One To Do",toDo.toDos[oneToDo])
//               for (let a of oneToDo){
//                 console.log(a);
//                 for(let b of a){
//                   console.log(b);
//                 }
//               }
//             }
//         }
//       });
//     }
//   )
//   .catch(function(err) {
//     console.log('Fetch Error :-S', err);
//   });