package Frame.UnitVisual;

public enum VisualType {
	SWARMLING 			("UnitVisualRessources/SwarmlingIMG.png"		,9	,4),
	SWARMLING_CARRIER 	("UnitVisualRessources/SwarmlingCarrierIMG.png"	,9	,4),
	SWARMIDE 			("UnitVisualRessources/SwarmideIMG.png"			,15	,7),
	SWARMODON 			("UnitVisualRessources/SwarmodonIMG.png"		,21 ,10),
	TERANLING			("UnitVisualRessources/TeranlingIMG.png"		,11	,5),
	TERANIDE			("UnitVisualRessources/TeranideIMG.png"			,15	,7),
	TERANODON			("UnitVisualRessources/TeranodonIMG.png"		,19	,9),
	D_TERANLING			("UnitVisualRessources/D_TeranlingIMG.png"		,11	,5),
	D_TERANIDE			("UnitVisualRessources/D_TeranideIMG.png"		,15	,7),
	D_TERANODON			("UnitVisualRessources/D_TeranodonIMG.png"		,19	,9),
	OVERMIND			("UnitVisualRessources/OvermindIMG.png"			,29	,14);	
	
	private final String _imgPath;
	private final int _size;
	private final int _half;
	
	VisualType(String aImgPath, int aSize, int aHalfCut){
		this._imgPath = aImgPath;
		this._size = aSize;
		this._half = aHalfCut;
	}
	
	public String path() { return _imgPath; }
	public int size() { return _size; }
	public int half() { return _half; }

}
