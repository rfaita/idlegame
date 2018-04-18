import { Resource } from '../resource';
import { LootRollValue } from './lootRollValue';
export class LootRoll {
    public id: String;
    public name: String;
    public desc: String;
    public type: String;
    public cost: Resource[];
    public rolls: Map<String, LootRollValue[]>;

}