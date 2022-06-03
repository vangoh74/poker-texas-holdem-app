import GalleryTable from "./GalleryTable";
import UseTableItems from "../hooks/useTableItems";

export default function PokerRoomOverview() {

    const tableItems = UseTableItems();

    return (
        <div className={"tablesGallery"}>
            {
                tableItems.map( tableItem => <GalleryTable tableItem={tableItem} />)
            }
        </div>
    );
}