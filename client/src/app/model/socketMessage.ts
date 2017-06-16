export class SocketMessage {
    public object: any;
    public action: string;

    constructor(object?: any, action?: string) {
        this.object = object;
        this.action = action;
    }

}