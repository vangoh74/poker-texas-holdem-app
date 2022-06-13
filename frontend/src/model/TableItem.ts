type Card = {
    rank: string
    suit: string
}

type Player = {
    playerName: string
    playerChips: number
    playerCards: Card[]
    playerImage: string
}

type Seat = {
    seatNumber: number
    playerName: string
}

type RoundState = {
    roundState: RoundState
}

export type TableItem = {
    id: string
    roundNumber: number
    roundState: RoundState
    bigBlind: number
    smallBlind: number
    maxSize: number
    freeSeats: number
    tableChips: number
    tableCards: Card[]
    players: Player[]
    seats: Seat[]
}
