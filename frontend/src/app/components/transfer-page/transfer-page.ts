import { Component } from '@angular/core';
import { TransferForm } from '../transfer-form/transfer-form';
import { TransferList } from '../transfer-list/transfer-list';

@Component({
    selector: 'app-transfer-page',
    imports: [TransferForm, TransferList],
    templateUrl: './transfer-page.html',
    styleUrl: './transfer-page.css',
})
export class TransferPage {}
