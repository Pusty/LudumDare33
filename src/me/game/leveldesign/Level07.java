package me.game.leveldesign;

import java.util.ArrayList;
import java.util.List;

import me.engine.entity.Entity;
import me.engine.entity.EntityBlock;
import me.engine.entity.EntityBloodSlime;
import me.engine.entity.EntityBombChicken;
import me.engine.entity.EntityChicken;
import me.engine.entity.EntityGoal;
import me.engine.entity.EntityGoldChicken;
import me.engine.entity.EntityHuman;
import me.engine.entity.EntityPortal;
import me.engine.location.Location;
import me.engine.main.MainClass;
import me.engine.world.LevelScript;
import me.engine.world.World;

public class Level07 extends LevelScript{

	boolean triggered;
	public Level07() {
		super(1);
	}
	public void init(MainClass m){
		triggered=false;
	}
	public void addPortal(MainClass m ,World w, int metaID, int x, int z) {
		if(metaID-7 == 1 || metaID-7 == 2 || metaID-7 == 3){
			w.addEntity(new EntityBlock(m,x,z,metaID-7));
		}else{
		super.addPortal(m,w,metaID,x,z);
		}
	}
	
	public void addEnemy(MainClass m ,World w, int metaID, int x, int z) {
		w.addEntity(new EntityGoldChicken(m,x,z));
	}
	public void addSpecial1(MainClass m ,World w, int metaID, int x, int z) {
		w.addEntity(new EntityBombChicken(m,x,z));
	}
	public void addSpecial2(MainClass m ,World w, int metaID, int x, int z) {
		w.addEntity(new EntityGoal(m,x,z));
	}
	
	public void mapTick(MainClass m){
		if(!triggered){
						for(int index2=0;index2<m.getWorld().getEntityArray().length;index2++){
							Entity e2 = m.getWorld().getEntityArray()[index2];
							if(e2 != null && e2 instanceof EntityBlock && ((EntityBlock) e2).getBlockID()<=(int)m.getSavedData().getData("block")){
								((EntityBlock) e2).setDead(true);
							}
						}
						if((int)m.getSavedData().getData("block")>=3)
							triggered=true;
					}
			
	}

}
