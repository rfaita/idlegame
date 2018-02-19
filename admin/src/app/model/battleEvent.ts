import { Buff } from './buff';
export class BattleEvent {
    public actionType: String;
    public buffEffectType: String;
    public value: Number;

    public subType:String;

    public damageType:String;
    public attributeType:String;

    public buff: Buff;
}