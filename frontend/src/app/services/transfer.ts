import { inject, Injectable } from '@angular/core';
import type { Observable } from 'rxjs';
import type { Transfer } from '../models/transfer.model';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class TransferService {
    private readonly http = inject(HttpClient);
    private readonly apiUrl = 'http://localhost:5000/transfers';

    findAll(): Observable<Transfer[]> {
        return this.http.get<Transfer[]>(this.apiUrl);
    }

    scheduleTransfer(transfer: Transfer): Observable<Transfer> {
        return this.http.post<Transfer>(this.apiUrl, transfer);
    }
}
