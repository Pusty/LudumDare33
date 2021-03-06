package me.engine.entity;


import java.util.Random;

import me.engine.main.MainClass;


public class EntityMonster extends EntityLiving {
	protected String playerName;
	public EntityMonster(MainClass m,float x, float y, String name) {
		super(m ,x, y,1f,1f);
		playerName = name;
	}

	public String getDialogText(Random r){
		return "DIALOG HERE";
	}
	
	
	public Entity createByString(MainClass m,String s,String split){
		Entity e = null;
		System.out.println("Initing "+getName());
		try{
		int x = Integer.parseInt(s.split(split)[0]);
		int z = Integer.parseInt(s.split(split)[1]);
		String name = s.split(split)[2];
		e=new EntityMonster(m,x,z,name);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return e;
	}
	public String createToString(MainClass m,String split){
		String s="";
		s=s+(int)this.getX();
		s=s+split;
		s=s+(int)this.getZ();
		s=s+split;
		s=s+playerName;
		s=s+"\n";
		return s;
	}
	
	public float getSpeed(){return 0.01f;}
	protected EntityMonster(){/*USED FOR DUMMY*/}
	public  String getName(){return "EntityMonster";}



//	public void render(MainClass m){
//		super.render(m);
//}
}
