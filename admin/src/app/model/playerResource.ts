import { Resource } from './resource';
export class PlayerResource {
    public id: String;
    public player: String;
    public lastTimeResourcesCollected: Date;
    public resources: Resource[];
}