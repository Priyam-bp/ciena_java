export type ShelfSummaryData ={
    shelf:{
        name: string,
        shelfType: string
    } | undefined,
    shelfPositions:Array<{
        shelfPosition:{
            name:string
        }
        device: Array< {
            name: string,
            deviceType: string
        }>
    }> | []
}
