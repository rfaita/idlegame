import { ChatRoomUser } from './chatRoomUser';
export class ChatRoom {
    public id: String;
    public name: String;
    public description: String;
    public connectedUsers: ChatRoomUser[];
}