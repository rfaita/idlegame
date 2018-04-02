import { Reward } from './reward';
export class Mail {
    public id: String;
    public date: Date;

    public fromUserId: String;
    public fromUserNickName: String;
    public toUserId: String;
    public toUserNickName: String;
    public reward: Reward;
    public text: String;

    public fromAdmin: Boolean;

    public readed: Boolean
    public collected: Boolean

    public publicMail: Boolean
}