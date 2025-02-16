import { ShelfPosition } from "../shelfPosition/shelf-position";

export type Device = {
    id?: number;
    name: string;
    deviceType: string;
    shelfPositions?: Array<ShelfPosition>;
}
