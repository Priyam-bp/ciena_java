import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
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
    }
];
