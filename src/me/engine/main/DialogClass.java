package me.engine.main;

public class DialogClass {
	static boolean[] inMapIndex=new boolean[16];
	public static void setDialog(MainClass m,int mapID){
		int dialogIndex=(int)m.getSavedData().getData("dialog");
		if(dialogIndex==mapID)return;
		inMapIndex=new boolean[16];
		for(int i=0;i<inMapIndex.length;i++){
			inMapIndex[i]=false;
		}
		
		if(mapID == 1){
		m.setDialog("Me",
				":O IM SO HUNGRY! AINT A FEW CHICKENS NEARBY?");
		}else if(mapID == 2){
			m.setDialog("Me",
					"WOT? I THOUGHT SLIMES WHERE FRINDLY!");
		}
		
		m.getSavedData().putData("dialog", mapID);
	}
	public static void randomDialogTick(MainClass m){
		int mapID = (int)m.getSavedData().getData("world");
			if(mapID == 1){
				if(!inMapIndex[0] && m.getWorld().getEntityArraySize() == 0){
					m.setDialog("Me",
							"YAM YAM THOSE WERE TASTY!");
					inMapIndex[0]=true;
				}
				//START MAP
			}else if(mapID == 2){
					if(!inMapIndex[0] && m.getWorld().getEntityArraySize() == 0){
						m.setDialog("Me",
								"Hmm? Maybe attacking random chickens was wrong?");
						inMapIndex[0]=true;
					}
					//SlimeMap
			}
	}
}
