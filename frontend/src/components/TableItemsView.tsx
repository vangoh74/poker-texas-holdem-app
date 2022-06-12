import {TableItem} from "../model/TableItem";
import TableCards from "./TableCards";
import "./css/TableItemsView.css"
import Player from "./Player";

type TableItemsViewProps = {
    tableItem : TableItem;
}

export default function TableItemsView({tableItem} : TableItemsViewProps) {

    return (
        <div className={"game-room"}>

            <h4>Table ID: {tableItem.id}</h4>

            <div className={"grid-container"}>

                <div className={"seat-1 bg-seats"}></div>
                <div className={"seat-2 bg-seats"}>
                    {tableItem.players.map(player => <Player key={player.playerName} player={player} />) }
                </div>
                <div className={"seat-3 bg-seats"}></div>

                <div className={"seat-10 bg-seats"}></div>
                <div className={"table bg-table"}>
                    <div className={"table-cards"}>
                        {tableItem.tableCards.map(card => <TableCards key={tableItem.id} card={card} />) }
                        <div className={"table-chips"}>Pot:{tableItem.tableChips}</div>
                    </div>
                </div>
                <div className={"seat-4 bg-seats"}></div>

                <div className={"seat-9 bg-seats"}></div>
                <div className={"seat-5 bg-seats"}></div>

                <div className={"seat-8 bg-seats"}></div>
                <div className={"seat-7 bg-seats"}></div>
                <div className={"seat-6 bg-seats"}></div>

            </div>

            <div className={"bet-container"}>
                <button className={"bet-btn"} >fold</button>
                <button className={"bet-btn"} >call</button>
                <button className={"bet-btn"} >raise</button>

            </div>
        </div>
    )
}
