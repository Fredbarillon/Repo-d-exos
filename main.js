import Person from "./person.js";
const nameField = document.querySelector("#name");
const dateField = document.querySelector("#date");
const timeField = document.querySelector("#time");
const participantField = document.querySelector("#nbParticipants");
const form = document.querySelector("form");
const nameAlert = document.querySelector("#nameAlert");; 
const timeAlert = document.querySelector("#timeAlert");
const participantAlert = document.querySelector("#participantAlert");

const mapPersons = new Map()
let counter = 0;
let error = false;

table
const table = document.createElement("table");
table.style.width = "500px";
table.style.margin = "auto";
table.style.height = "auto";
const thead = document.createElement("thead");
const headerRow = document.createElement("tr");

["Nom", "Date", "Heure", "Participants"].forEach(tittle => {
    const th = document.createElement("th");
    th.innerText = tittle;
    headerRow.appendChild(th);
});
thead.appendChild(headerRow);
table.appendChild(thead);
table.style.border = "1px solid black";
            table.style.textAlign = "center";
            thead.style.backgroundColor = "lightblue";
           

addeventlisteners

nameField.addEventListener("input",(event)=>{
    const input =  event.target
    
    if(!input.value || input.value.length < 3 || /\d/.test(input.value)){
        nameAlert.style.display = "block" 
        error = true;   
    }else{
        nameAlert.style.display = "none"
        error = false;
    }
});

timeField.addEventListener("input",(event)=>{
    const input =event.target
    const value = input.value
    function timeStringToFloat(value) {
        let hoursMinutes = value.split(/[.:]/);
        let hours = parseInt(hoursMinutes[0], 10);
        let minutes = hoursMinutes[1] ? parseInt(hoursMinutes[1], 10) : 0;
        return hours + minutes / 60;
    }
    let transformedTime = timeStringToFloat(value);
    // console.log(transformedTime);
    
    if(!value || transformedTime < 9 || transformedTime > 18){
        timeAlert.style.display = "block"
        error = true;
    } else{
        timeAlert.style.display = "none"
                error = false;
            }
        });
        
        participantField.addEventListener("input",(event)=>{
            const input =  event.target
            
            if(!input.value || input.value < 0 || input.value > 10) {
                participantAlert.style.display = "block"
                error = true;    
            }else{
                participantAlert.style.display = "none"
            error = false;
        }
    });
    
    form.addEventListener("submit",(event)=>{
        event.preventDefault()
        
        if (!error) {
            // object
            const person = new Person (
                nameField.value,
                dateField.value,
                timeField.value,
                participantField.value
            )

            // check if date is valid
            let appointment = false;
            mapPersons.forEach((value) => {
                if (value.date === person.date && value.timeSlot === person.timeSlot) {
                    appointment = true;
                }
            });
            
            if (appointment) {
                alert("⚠️ Ce créneau est déjà réservé.");
                return;
            }
            
            const date = new Date(person.date);
            const today = new Date();
            if (date < today) {
                setTimeout(alert("⚠️ Date antérieure impossible.", 3000));
                return;
              }; 

            // map setings + to json
            mapPersons.set(counter++,person); 
            const mapToJson = (mapPersons) => {
                return JSON.stringify(Object.fromEntries(mapPersons));
              };
              localStorage.setItem("appointments", mapToJson(mapPersons));
              console.log(localStorage.getItem("appointments"));


        
            table
            const tbody = document.createElement("tbody");
            const tr = document.createElement("tr");
            const tdName = document.createElement("td");
            tdName.innerText = person.name;
            const tdDate = document.createElement("td");
            tdDate.innerText = person.date;
            const tdTime = document.createElement("td");
            tdTime.innerText = person.time;
            const tdParticipants = document.createElement("td");
            tdParticipants.innerText = person.participants;
            tr.appendChild(tdName);
            tr.appendChild(tdDate);
            tr.appendChild(tdTime);
            tr.appendChild(tdParticipants);
            tbody.appendChild(tr);
            
            table.appendChild(tbody);
            document.body.appendChild(table);
            tr.style.padding = "10px";
            tr.style.border = "1px solid black";

        }   else {
            setTimeout(alert("⚠️ Veuillez remplir le formulaire correctement.", 5000));
            }
        });
    
//         // table of appointments
//         const createTable = () => {
//           const getStorage = localStorage.getItem("appointments");
//           const parsedStorage = JSON.parse(getStorage);

//           const keys = Object.keys(parsedStorage); 
          
          
          
//           const tableA = document.createElement('table');
//           const header = document.createElement('tr');
//           const thead = document.createElement("thead");

        
//           ["Nom", "Date", "Heure", "Participants"].forEach((titre) => {
//             const th = document.createElement("th");
//             th.innerText = titre;
//             header.appendChild(th);
//           });
        
//           thead.appendChild(header);
//           tableA.appendChild(thead);
        
//           const tbody = document.createElement("tbody");
        
//           keys.forEach((key) => {
//             const person = parsedStorage[key];
//             const tr = document.createElement("tr");
        
//             const tdName = document.createElement("td");
//             tdName.innerText = person.name;
        
//             const tdDate = document.createElement("td");
//             tdDate.innerText = person.date;
        
//             const tdTime = document.createElement("td");
//             tdTime.innerText = person.time;
        
//             const tdParticipants = document.createElement("td");
//             tdParticipants.innerText = person.participants;
        
//             tr.appendChild(tdName);
//             tr.appendChild(tdDate);
//             tr.appendChild(tdTime);
//             tr.appendChild(tdParticipants);
//             tbody.appendChild(tr);
//           });
        
//           tableA.appendChild(tbody);
//           document.body.appendChild(tableA);
//         }
// createTable();