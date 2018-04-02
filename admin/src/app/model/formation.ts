import { BattlePositionedHero } from './battlePositionedHero';
export class Formation {

    public id: String;
    public userId: String;

    public heroes: BattlePositionedHero[];

    public formationAllocation: String;
    public nextLevelFormation: String;
    //public reward: Reward;

}