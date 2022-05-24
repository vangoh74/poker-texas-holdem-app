import "./css/TableCards.css"

const enum Suit {
    DIAMONDS ='♦',
    CLUBS = '♣',
    HEARTS = '♥',
    SPADES = '♠'
}

const enum Rank {

    ACE = 'A',
    TWO = '2',
    THREE = '3',
    FOUR = '4',
    FIVE = '5',
    SIX = '6',
    SEVEN = '7',
    EIGHT = '8',
    NINE = '9',
    TEN = '10',
    JACK = 'J',
    QUEEN = 'Q',
    KING = 'K'
}

type Card = {
    rank : String,
    suit : String
}

type TableCardsProps = {
    card : Card
}

export default function TableCards({card} : TableCardsProps) {
    switch (card.suit) {
        case "DIAMONDS" :
            card.suit = Suit.DIAMONDS;
            break;
        case "HEARTS" :
            card.suit = Suit.HEARTS;
            break;
        case "CLUBS" :
            card.suit = Suit.CLUBS;
            break;
        case "SPADES" :
            card.suit = Suit.SPADES;
            break;
    }

    switch (card.rank) {
        case "ACE" :
            card.rank = Rank.ACE
            break;
        case "TWO" :
            card.rank = Rank.TWO
                break;
        case "THREE" :
            card.rank = Rank.THREE
                break;
        case "FOUR" :
            card.rank = Rank.FOUR
                break;
        case "FIVE" :
            card.rank = Rank.FIVE
                break;
        case "SIX" :
            card.rank = Rank.SIX
                break;
        case "SEVEN" :
            card.rank = Rank.SEVEN
                break;
        case "EIGHT" :
            card.rank = Rank.EIGHT
                break;
        case "NINE" :
            card.rank = Rank.NINE
                break;
        case "TEN" :
            card.rank = Rank.TEN
                break;
        case "JACK" :
            card.rank = Rank.JACK
                break;
        case "QUEEN" :
            card.rank = Rank.QUEEN
                break;
        case "KING" :
            card.rank = Rank.KING
                break;
    }
    console.log(card.rank, Rank.TWO)
    return (
        <div className={"card"}>{card.rank } {card.suit}</div>
    )
}
