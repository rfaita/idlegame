import { PositionedHero } from './positionedHero';
import { BattlePositionedHero } from './battlePositionedHero';
import { BattleEvent } from './battleEvent';
import { Buff } from './buff';

export class BattleLog {
    public turn: number;
    public heroOrigin: BattlePositionedHero;
    public battleEvent: BattleEvent;
    public heroesTarget: BattlePositionedHero[];
    public buffDone: Buff;

}
