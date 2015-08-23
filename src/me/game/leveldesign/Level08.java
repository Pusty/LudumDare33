package me.game.leveldesign;

import java.util.ArrayList;
import java.util.List;

import me.engine.entity.EntityBloodSlime;
import me.engine.entity.EntityChicken;
import me.engine.entity.EntityChickenBoss;
import me.engine.entity.EntityGoal;
import me.engine.entity.EntityItem;
import me.engine.entity.EntityPortal;
import me.engine.entity.EntitySlime;
import me.engine.location.Location;
import me.engine.main.MainClass;
import me.engine.world.LevelScript;
import me.engine.world.World;

public class Level08 extends LevelScript{

	List<Location> locs;
	List<Integer> metas;
	boolean triggered;
	public Level08() {
		super(1);
	}
	public void init(MainClass m){
		locs = new ArrayList<Location>();
		metas = new ArrayList<Integer>();
		triggered=false;
	}
	public void addEnemy(MainClass m ,World w, int metaID, int x, int z) {
		w.setBoss(new EntityChickenBoss(m,x,z));
	}
	public void addSpecial1(MainClass m ,World w, int metaID, int x, int z) {
		w.addEntity(new EntitySlime(m,x,z));
	}
	public void addSpecial2(MainClass m ,World w, int metaID, int x, int z) {
		w.addEntity(new EntityItem(m,x,z));
	}
	public void addPortal(MainClass m ,World w, int metaID, int x, int z) {
		if(metaID-7!=metaID){
		 locs.add(new Location(x,z));
		 metas.add(metaID-7);
		}else
			m.getWorld().addEntity(new EntityPortal(m,x,z,true,metaID-7,true));
	}
	public void mapTick(MainClass m){
		if(m.getWorld().getEntityArraySize()==0 && m.getWorld().getBoss() != null && m.getWorld().getBoss().getHealth() < 1 &&  !triggered){
			for(int index=0;index<locs.size();index++)
			m.getWorld().addEntity(new EntityPortal(m,locs.get(index).getX(),locs.get(index).getZ(),false,metas.get(index),true));
			triggered=true;
		}
	}
}
