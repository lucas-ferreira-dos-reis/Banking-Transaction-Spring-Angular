import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { RouterLink } from '@angular/router';

@Component({
    selector: 'app-home-page',
    imports: [
        MatButtonModule,
        MatCardModule,
        MatChipsModule,
        MatIconModule,
        MatTooltipModule,
        RouterLink,
    ],
    templateUrl: './home-page.html',
    styleUrl: './home-page.css',
})
export class HomePage {
    readonly backendTechs = [
        {
            text: 'Java 11',
            tooltip: 'The assignment required Java 11.',
        },
        {
            text: 'Spring Boot 2.7.18',
            tooltip: 'The latest Spring Boot version compatible with Java 11.',
        },
        {
            text: 'Spring Data JPA',
            tooltip: 'Used for database persistence and repository abstraction.',
        },
        {
            text: 'H2 Database',
            tooltip: 'The assignment required an in-memory database.',
        },
        {
            text: 'WebSocket / STOMP',
            tooltip:
                'Implemented to provide real-time statement updates, although it was not required by the assignment.',
        },
        {
            text: 'JUnit 5 & MockMvc',
            tooltip:
                'Added to ensure reliable unit and integration testing, although it was not required by the assignment.',
        },
        {
            text: 'OpenAPI / Swagger',
            tooltip:
                'Added to provide interactive API documentation, although it was not required by the assignment.',
        },
    ];

    readonly frontendTechs = [
        'Angular 22',
        'TypeScript',
        'Angular Material',
        'RxJS & Signals',
        'SockJS / STOMP Client',
        'Standalone Components',
    ];
}
