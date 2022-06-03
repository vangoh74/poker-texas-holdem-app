type Card = {
    rank: string;
    suit: string;
}

export type TableItem = {
    id: string
    bigBlind: number
    tableSize: number
    freeSeats: number
    roundNumber: number
    tableCards: Card[]
}
