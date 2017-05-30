import { PositionedHero } from './positionedHero';

export class Battle {
    public id: number;
    public winner: string;
    public formationAllocation: string;
    public size: number;
    public heroes: PositionedHero;

}
