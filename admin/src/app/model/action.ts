import { ActionEffect } from './actionEffect';
export class Action {
    public special: Boolean;
    public mainActionEffect: ActionEffect;
    public secundaryActionsEffects: ActionEffect[];
}