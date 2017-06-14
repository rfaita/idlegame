import { PositionedHero } from './positionedHero';

export class Formation {
    public id: number;
    public formationType: string;
    public formationAllocation: string;
    public size: number;
    public heroes: PositionedHero[];

}
