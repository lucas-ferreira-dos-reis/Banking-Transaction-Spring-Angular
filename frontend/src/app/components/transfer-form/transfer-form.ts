import { Component, inject, output } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { TransferService } from '../../services/transfer';

@Component({
    selector: 'app-transfer-form',
    imports: [
        ReactiveFormsModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatSnackBarModule,
    ],
    templateUrl: './transfer-form.html',
    styleUrl: './transfer-form.css',
})
export class TransferForm {
    private fb = inject(FormBuilder);
    private transferService = inject(TransferService);
    private snackBar = inject(MatSnackBar);

    readonly transferScheduled = output<void>();

    transferForm = this.fb.group({
        sourceAccount: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
        destinationAccount: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
        amount: [null as number | null, [Validators.required, Validators.min(0.01)]],
        scheduledDate: [null as Date | null, Validators.required],
    });

    onSubmit() {
        if (this.transferForm.valid) {
            const formValue = this.transferForm.value;
            const payload: any = {
                ...formValue,
                scheduledDate: formValue.scheduledDate?.toISOString().split('T')[0],
            };

            this.transferService.scheduleTransfer(payload).subscribe({
                next: () => {
                    this.snackBar.open('Transfer scheduled successfully!', 'Close', {
                        duration: 5000,
                    });
                    this.transferForm.reset();
                    this.transferScheduled.emit();
                },
                error: (err) => {
                    const message = err.error?.message || 'Error scheduling transfer!';
                    this.snackBar.open(message, 'Close', { duration: 10000 });
                },
            });
        }
    }
}
