import {TableItem} from "../model/TableItem";
import "./css/TableMiniature.css"
import {useNavigate} from "react-router-dom";

type TableProps = {
    tableItem : TableItem;
}

export default function TableMiniature({tableItem} : TableProps) {

    const navigate = useNavigate();

    return (
        <div className={"table-miniature"}>
            <div className={"table-title"}>Table: Big-Blind {tableItem.bigBlind}</div>
            <div className={"table-box"}>
                <p>Table size: {tableItem.tableSize}</p>
                <p>free seats: {tableItem.freeSeats}</p>
                <div>
                    <button className={"take-a-seat-btn"}
                            onClick={() => navigate("/tableItems/:id")}>
                        take a seat
                    </button>
                </div>
            </div>
        </div>
    )
}
