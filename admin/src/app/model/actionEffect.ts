import { BuffEffect } from './buffEffect';
export class ActionEffect {

    public type: String;
    public targetType: String;
    //Habilidade pode errar?
    public canBeDodge: Boolean;
    //Percentual do dano que sera utilizado no efeito caso o mesmo necessite
    public percentage: Number;
    public damageType: String;
    public overSameTeam: Boolean;
    public buffEffects: BuffEffect[];


}