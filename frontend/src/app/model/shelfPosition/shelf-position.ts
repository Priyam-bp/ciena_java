import { Device } from "../device/device";
import { Shelf } from "../shelf/shelf";

export type ShelfPosition  = {
    id?: number;
    name: String;
    device?: Device;
    shelf?: Shelf
}
