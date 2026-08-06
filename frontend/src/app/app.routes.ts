import { Routes } from '@angular/router';
import { TransferPage } from './components/transfer-page/transfer-page';
import { HomePage } from './components/home-page/home-page';

export const routes: Routes = [
    { path: '', redirectTo: 'transfers', pathMatch: 'full' },
    { path: 'transfers', component: TransferPage },
    { path: 'home', component: HomePage },
    { path: '**', redirectTo: 'transfers' },
];
