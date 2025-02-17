import { ShelfPosition } from "../shelfPosition/shelf-position";

export type Device = {
    id?: number;
    name: string | undefined;
    deviceType: string | undefined;
    shelfPositions?: Array<ShelfPosition>;
}
