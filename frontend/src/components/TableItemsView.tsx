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

                {tableItem.players.map((player, index) =>
                    <div className={`player-${index + 1} bg-seats`}>
                        <Player key={player.playerName} player={player} />
                    </div>)
                }

                <div className={"table bg-table"}>
                    <div className={"table-cards"}>
                        {tableItem.tableCards.map(card => <TableCards key={tableItem.id} card={card} />) }
                        <div className={"table-chips"}>Pot:{tableItem.tableChips}</div>
                    </div>
                </div>
            </div>

            <div className={"actions-container"}>
                <button className={"actions-btn"} >fold</button>
                <button className={"actions-btn"} >call</button>
                <button className={"actions-btn"} >raise</button>
            </div>
        </div>
    )
}
/*
"<button className={`${tableItem.action_1}-btn`} >fold</button>\n" +
"                <button className={`${tableItem.action_2}-btn`} >call</button>\n" +
"                <button className={`${tableItem.action_3}-btn`} >raise</button>"*/