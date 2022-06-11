import "./css/Player.css"
import "./css/TableCards.css"
import TableCards from "./TableCards";

type Card = {
    rank : string
    suit : string
}

type Player = {
    playerName: string
    playerChips: number
    playerCards: Card[]
    playerImage: string
}

type PlayerProps = {
    player: Player
}

export default function Player({player} : PlayerProps) {


    return (
        <div className={"seat"}>
            <div >
                <div className={"player-cards"}>{player.playerCards.map(card => <TableCards card={card} />)}</div>
                <div>{player.playerName}</div>
                <div>{player.playerChips}</div>
            </div>
        </div>
    )
}