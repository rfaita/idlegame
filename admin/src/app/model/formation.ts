import { BattlePositionedHero } from './battlePositionedHero';
export class Formation {

    public id: String;
    public player: String;

    public heroes: BattlePositionedHero[];

    public formationAllocation: String;
    public nextLevelFormation: String;
    //public reward: Reward;

}