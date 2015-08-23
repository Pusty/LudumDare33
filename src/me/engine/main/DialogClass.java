package me.engine.main;

import me.engine.entity.Entity;
import me.engine.entity.EntityBlock;
import me.engine.entity.EntityBombChicken;
import me.engine.entity.EntityGoal;
import me.engine.entity.EntityHuman;
import me.engine.entity.EntityLiving;

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
				":O Im so hungry! aint a few chickens nearby?");
		}else if(mapID == 2){
			m.setDialog("Me",
					"what? I thought slimes were friendly!");
		}else if(mapID == 3){
			m.setDialog("Me",
					"why is everyone scared? am I the bad one here? This golden chickens looks absolutly delicious!");
		}else if(mapID == 4){
			m.setDialog("Me",
					"exploding chickens? something is wrong here... Why am I shooting feathers? :O");
		}else if(mapID == 5){
			m.setDialog("Me",
					"why are those slimes shoting things? Why am I able to use the attacks slimes use? Whats wrong with this world? Something seems off...");
		}else if(mapID == 6){
			m.setDialog("Me",
					"Oh! Another human being! Maybe it can tell me what is going on!");
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
			}else if(mapID == 3){
				if(!inMapIndex[0] && m.getWorld().getEntityArraySize() == 0){
					m.setDialog("Me",
							"Why am I glowing so bright?");
					inMapIndex[0]=true;
				}
				//Golden Chicken
			}else if(mapID == 4){
				if(!inMapIndex[0]){
					boolean bool=false;
					for(int index=0;index<m.getWorld().getEntityArray().length;index++){
						Entity e = m.getWorld().getEntityArray()[index];
						if(e == null || !(e instanceof EntityBombChicken) || !((EntityLiving) e).isDead())continue;
						bool=true;
						break;
					}if(bool){
					m.setDialog("Me",
							"Those chickens are dangerous! I hope I dont get blown up!");
					inMapIndex[0]=true;
					}
				}
				//Exploding Chicken
			}else if(mapID == 5){
				if(!inMapIndex[0]){
					boolean bool=false;
					for(int index=0;index<m.getWorld().getEntityArray().length;index++){
						Entity e = m.getWorld().getEntityArray()[index];
						if(e == null || !(e instanceof EntityBlock))continue;
						if(((EntityBlock)e).isDead()){inMapIndex[0]=true;break;}
						if(GameTickHandler.inRange(e.getLocation(),m.getWorld().getPlayer().getLocation(), 5)){
						bool=true;
						break;
						}
					}if(bool){
					m.setDialog("Me",
							"A wall? Where is this wall coming from? It wasnt here befor! Am I crazy? How can I get pass this thing?");
					inMapIndex[0]=true;
					}
				}
				//Finding I wall
			}else if(mapID == 6){
				if(!inMapIndex[0]){
					boolean bool=false;
					for(int index=0;index<m.getWorld().getEntityArray().length;index++){
						Entity e = m.getWorld().getEntityArray()[index];
						if(e == null || !(e instanceof EntityHuman))continue;
						if(GameTickHandler.inRange(e.getLocation(),m.getWorld().getPlayer().getLocation(), 5)){
						bool=true;
						break;
						}
					}if(bool){
					m.setDialog("Me",
							"Why is it attacking me? Is the whole world against me? What is going on!?");
					inMapIndex[0]=true;
					}
				}
				//Seeing the other player
				if(!inMapIndex[1]){
					boolean bool=false;
					for(int index=0;index<m.getWorld().getEntityArray().length;index++){
						Entity e = m.getWorld().getEntityArray()[index];
						if(e == null || !(e instanceof EntityGoal))continue;
						if(GameTickHandler.inRange(e.getLocation(),m.getWorld().getPlayer().getLocation(), 6)){
						bool=true;
						break;
						}
					}if(bool){
					m.setDialog("Me",
							"Hmm... walls again? And something that looks like it can trigger something?");
					inMapIndex[1]=true;
					}
				}
				//Seeing the goal
			}
	}
}
