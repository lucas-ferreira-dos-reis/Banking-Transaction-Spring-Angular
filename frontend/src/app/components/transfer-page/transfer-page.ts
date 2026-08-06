import { Component } from '@angular/core';
import { TransferForm } from '../transfer-form/transfer-form';
import { TransferList } from '../transfer-list/transfer-list';
import { FeeTable } from '../fee-table/fee-table';

@Component({
    selector: 'app-transfer-page',
    imports: [TransferForm, TransferList, FeeTable],
    templateUrl: './transfer-page.html',
    styleUrl: './transfer-page.css',
})
export class TransferPage {}
