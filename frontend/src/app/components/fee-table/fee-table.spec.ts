import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeeTable } from './fee-table';

describe('FeeTable', () => {
    let component: FeeTable;
    let fixture: ComponentFixture<FeeTable>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [FeeTable],
        }).compileComponents();

        fixture = TestBed.createComponent(FeeTable);
        component = fixture.componentInstance;
        await fixture.whenStable();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
