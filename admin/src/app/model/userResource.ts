import { Resource } from './resource';
export class UserResource {
    public id: String;
    public userId: String;
    public lastTimeResourcesCollected: Date;
    public resources: Resource[];
}