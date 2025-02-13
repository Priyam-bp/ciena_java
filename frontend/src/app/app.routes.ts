import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path:'',
        pathMatch:'full',
        loadComponent: async ()=>{
            const m = await import("../app/components/home/home.component");
            return m.HomeComponent;
        }
    },
    {
        path: 'devices',
        pathMatch:'full',
        loadComponent: async ()=>{
            const m = await import('./components/device-list/device-list.component');
            return m.DeviceListComponent;
        }
    },
    {
        path: 'shelfPositions',
        pathMatch:"full",
        loadComponent: async () =>{
            const m = await import('./components/shelf-position-list/shelf-position-list.component');
            return m.ShelfPositionListComponent;
        }
    },
    {
        path: 'shelf',
        pathMatch:"full",
        loadComponent: async ()=>{
            const m = await import('./components/shelf/shelf.component');
            return m.ShelfComponent;
        }
    },
    {
        path:"adddevice",
        pathMatch:'full',
        loadComponent: async()=>{
            const m = await import('./components/add-device/add-device.component');
            return m.AddDeviceComponent;
        }
    },
    {
        path:'addshelf',
        pathMatch:'full',
        loadComponent: async()=>{
            const m = await import('./components/add-shelf/add-shelf.component');
            return m.AddShelfComponent;
        }
    },
    {
        path:'addshelfposition',
        pathMatch:'full',
        loadComponent: async()=>{
            const m = await import('./components/add-shelfposition/add-shelfposition.component')
            return m.AddShelfpositionComponent;
        }
    }
];
