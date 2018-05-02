import { Action } from './action';
export class BattleHeroType {

    public id: String;
    public quality: String;
    public faction: String;
    public role: String;
    public name: String;
    public specialActions: Action[];
    public defaultActions: Action[];
    public distanceType: String;
    //public List<Passive> passives;

   

}