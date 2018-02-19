import { BattlePositionedHero } from './battlePositionedHero';
import { BattleEvent } from './battleEvent';

export class BattleLog {
    
    public turn: Number;
    public heroOrigin: BattlePositionedHero;
    public battleEvent: BattleEvent;
    public heroesTarget: BattlePositionedHero[];

}