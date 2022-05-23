type Card = {
    rank : string;
    suit : string;
}

export type TableItem = {
    id : string;
    roundNbr : number;
    tableCards : Card[]
}
