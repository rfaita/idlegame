import { PositionedHero } from './positionedHero';
import { Formation } from './formation';

export class PvpRoll {
    public expireDate: number;
    public formationHigher: Formation;
    public formationLower: Formation;
    public formationRandom: Formation;
    public namePlayerHigher: string;
    public namePlayerLower: string;
    public namePlayerRandom: string;
    public pvpScoreHigher: number;
    public pvpScoreLower: number;
    public pvpScoreRandom: number;
}
