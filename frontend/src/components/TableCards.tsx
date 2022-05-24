import "./css/TableCards.css"
type Card = {
    rank : String,
    suit : string
}

type TableCardsProps = {
    card : Card
}

export default function TableCards({card} : TableCardsProps) {
    return (
        <div className={"tableMidCards"}>
            <div className={"card"}>{card.rank} {card.suit}</div>
        </div>
    )
}
