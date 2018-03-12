import { ActionEffect } from './actionEffect';
export class Action {
    public special: Boolean;
    public mainActionEffect: ActionEffect;
    public secundaryActionsEffects: ActionEffect[];

    public formationPositionType: String;

    constructor(fpt?: String) {
        this.formationPositionType = fpt;
    }
}