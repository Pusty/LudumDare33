package me.game.leveldesign;

import java.util.ArrayList;
import java.util.List;

import me.engine.entity.Entity;
import me.engine.entity.EntityBlock;
import me.engine.entity.EntityBloodSlime;
import me.engine.entity.EntityChicken;
import me.engine.entity.EntityPortal;
import me.engine.location.Location;
import me.engine.main.MainClass;
import me.engine.world.LevelScript;
import me.engine.world.World;

public class Level05 extends LevelScript{

	boolean triggered;
	public Level05() {
		super(5);
	}
	public void init(MainClass m){
		triggered=false;
	}
	public void addEnemy(MainClass m ,World w, int metaID, int x, int z) {
		w.addEntity(new EntityBloodSlime(m,x,z));
	}
	
	public void addSpecial2(MainClass m ,World w, int metaID, int x, int z) {
		w.addEntity(new EntityBlock(m,x,z,1));
	}

	public void mapTick(MainClass m){
		if(m.getWorld().getEntityArraySize()==0 && !triggered){
			for(int index=0;index<m.getWorld().getEntityArray().length;index++){
				Entity e = m.getWorld().getEntityArray()[index];
				if(e != null && e instanceof EntityBlock){
					((EntityBlock) e).setDead(true);
				}
			}
			triggered=true;
		}
	}
}
