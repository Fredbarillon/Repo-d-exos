// person.js

export default class Person {
  name
  date
  time
  participants
  
  constructor(name, date, time, participants) {
    this.name = name;
    this.date = date;
    this.time = time;
    this.participants = participants;
  }
  get timeSlot() {
    const [h] = this.time.split("h").map(Number);
    return h < 12 ? "AM" : "PM";
  }
}