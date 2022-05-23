type Card = {
    rank : String,
    suit : string
}

type TableCardsProps = {
    card : Card
}

export default function TableCards({card} : TableCardsProps) {
    return (
        <>
            <div>{card.rank} {card.suit}</div>
        </>
    )
}
