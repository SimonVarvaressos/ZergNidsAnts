package System;

enum TypeMessage
{
	Attack,
	GoTo,
	Detect,
	Loot,
	Detected
	
}

public class Message {

	public TypeMessage type;
	public Vector2 position;
	public String destinataire;
	
	Message(TypeMessage t, Vector2 pos, String dest)
	{
		type = t;
		position = pos;
		destinataire = dest;
	}
}
