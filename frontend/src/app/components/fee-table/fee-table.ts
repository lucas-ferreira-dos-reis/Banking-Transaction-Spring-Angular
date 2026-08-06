import { Component, signal } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import type { FeeRule } from '../../models/fee-rule.model';

@Component({
    selector: 'app-fee-table',
    imports: [MatCardModule, MatTableModule],
    templateUrl: './fee-table.html',
    styleUrl: './fee-table.css',
})
export class FeeTable {
    readonly tableData = signal<FeeRule[]>([
        { diffDays: 'From 0 to 0', fixedAmount: '3.00', percentageAmount: '2.5%' },
        { diffDays: 'From 1 to 10', fixedAmount: '12.00', percentageAmount: '0.0%' },
        { diffDays: 'From 11 to 20', fixedAmount: '0.00', percentageAmount: '8.2%' },
        { diffDays: 'From 21 to 30', fixedAmount: '0.00', percentageAmount: '6.9%' },
        { diffDays: 'From 31 to 40', fixedAmount: '0.00', percentageAmount: '4.7%' },
        { diffDays: 'From 41 to 50', fixedAmount: '0.00', percentageAmount: '1.7%' },
    ]);
    readonly displayedColumns: string[] = ['diffDays', 'fixedAmount', 'percentageAmount'];
}
