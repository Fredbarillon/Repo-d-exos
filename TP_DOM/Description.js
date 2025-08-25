export default class Description {
    constructor(content, number) {
        this.content = content;
        this.number = number;
    }

    showDescription() {
        console.log(`${this.number}: ${this.content}`);
    }
}
