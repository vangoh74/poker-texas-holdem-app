import {useParams} from "react-router-dom";
import useDetailedTableItems from "../hooks/useDetailedTableItems";
import {useEffect} from "react";
import TableItemsView from "../components/TableItemsView";
import "./css/DetailsPage.css";

export default function DetailsPage() {
    const {id} = useParams();
    const { detailedTableItems, getTableItemsByTableId } = useDetailedTableItems();

    useEffect( () => {
        if(id) {
            getTableItemsByTableId(id)
        }
    }, [id]);

    return (
        <div className={"gameRoom"}>
            {detailedTableItems &&
                <div>
                    <TableItemsView tableItem={detailedTableItems} />
                </div>}
        </div>
    )
}
