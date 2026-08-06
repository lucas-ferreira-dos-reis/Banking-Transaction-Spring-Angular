import { Injectable } from '@angular/core';
import { Client } from '@stomp/stompjs';
import { Subject, type Observable } from 'rxjs';
import SockJS from 'sockjs-client';
import type { Transfer } from '../models/transfer.model';

@Injectable({ providedIn: 'root' })
export class WebSocketService {
    private stompClient!: Client;
    private transferSubject = new Subject<Transfer>();

    constructor() {
        this.initWebSocket();
    }

    private initWebSocket() {
        this.stompClient = new Client({
            webSocketFactory: () => new SockJS('http://localhost:5000/api/ws'),
            reconnectDelay: 5000,
            debug: (str) => console.log(str),
        });

        this.stompClient.onConnect = () => {
            console.log('Connected to WebSocket!');
            this.stompClient.subscribe('/topic/transfers', (message) => {
                if (message.body) {
                    const newTransfer: Transfer = JSON.parse(message.body);
                    this.transferSubject.next(newTransfer);
                }
            });
        };

        this.stompClient.activate();
    }

    onNewTransfer(): Observable<Transfer> {
        return this.transferSubject.asObservable();
    }
}
