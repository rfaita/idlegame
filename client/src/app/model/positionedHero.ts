import { Hero } from './hero';

export class PositionedHero {
    public hero: Hero;
    public battlePosition: string;

    constructor(hero: Hero,
        battlePosition: string) {
        this.hero = hero;
        this.battlePosition = battlePosition;
    }

}