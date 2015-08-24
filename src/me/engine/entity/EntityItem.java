package me.engine.entity;

import me.engine.main.MainClass;


public class EntityItem extends Entity{
	MainClass main;
	public EntityItem(MainClass m,float x, float y) {
		super(x, y, 1f,1f);
		main=m;

	}
	
	protected EntityItem(){/*USED FOR DUMMY*/}
	
	public Entity createByString(MainClass m,String s,String split){
		Entity e = null;
		System.out.println("Initing "+getName());
		try{
		int x = Integer.parseInt(s.split(split)[0]);
		int z = Integer.parseInt(s.split(split)[1]);
		e=new EntityItem(m,x,z);
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
		s=s+"\n";
		return s;
	}

	public String getTextureName(int i){
		return "health";
	}
	
	
}