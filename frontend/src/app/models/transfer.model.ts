export interface Transfer {
    id?: number;
    sourceAccount: string;
    destinationAccount: string;
    amount: number;
    fee?: number;
    creationDate: string;
    scheduleDate?: string;
}
