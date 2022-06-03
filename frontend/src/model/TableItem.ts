type Card = {
    rank : string;
    suit : string;
}

export type TableItem = {
    id : string;
    roundNumber : number;
    tableCards : Card[]
}
