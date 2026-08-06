import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import type { Transfer } from '../../models/transfer.model';
import { TransferService } from './../../services/transfer';
import { WebSocketService } from '../../services/web-socket';
import type { Subscription } from 'rxjs';

@Component({
    selector: 'app-transfer-list',
    imports: [CommonModule, MatCardModule, MatTableModule],
    templateUrl: './transfer-list.html',
    styleUrl: './transfer-list.css',
})
export class TransferList {
    private readonly transferService = inject(TransferService);
    private readonly webSocketService = inject(WebSocketService);
    private wsSubscription!: Subscription;
    private snackBar = inject(MatSnackBar);

    readonly transfers = signal<Transfer[]>([]);
    readonly displayedColumns: string[] = [
        'id',
        'source',
        'destination',
        'amount',
        'fee',
        'transferDate',
        'schedulingDate',
    ];

    ngOnInit() {
        this.loadTransfers();

        this.wsSubscription = this.webSocketService.onNewTransfer().subscribe({
            next: (newTransfer) => {
                this.transfers.update((current) => [newTransfer, ...current]);
            },
        });
    }

    ngOnDestroy() {
        if (this.wsSubscription) {
            this.wsSubscription.unsubscribe();
        }
    }

    loadTransfers() {
        this.transferService.findAll().subscribe({
            next: (data) => this.transfers.set(data),
            error: (err) => {
                console.error('Error loading transfers!', err);
                this.snackBar.open('Error loading transfers! Try reloading the page!', 'Close', {
                    duration: 5000,
                });
            },
        });
    }
}
