export class Mail {
    public id: String;
    public date: Date;

    public fromUser: String;
    public fromNickName: String;
    public toUser: String;
    public toNickName: String;
    //public reward:Reward;
    public text: String;

    public fromAdmin: Boolean;

    public readed: Boolean
    public collected: Boolean

    public publicMail: Boolean
}