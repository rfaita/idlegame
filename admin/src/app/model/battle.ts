import { BattleFormation } from './battleFormation';
import { BattleLog } from './battleLog';
export class Battle {
    public battleLog: BattleLog[];
    public turn: Number;
    public attackFormation: BattleFormation;
    public defenseFormation: BattleFormation;
    
    public winner: BattleFormation;
}