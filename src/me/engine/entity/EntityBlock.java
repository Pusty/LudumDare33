package me.engine.entity;


import me.engine.main.MainClass;


public class EntityBlock extends Entity{
	MainClass main;
	boolean death;
	boolean isdead;
	int blockID;
	public EntityBlock(MainClass m,float x, float y,int block) {
		super(x, y, 1f,1f);
		main=m;
		death=false;
		isdead=false;
		blockID=block;
	}	
	
	public int getBlockID(){return blockID;}
	
	public boolean isDead(){return death;}
	public void setDead(boolean b){death=b;
	if(!isdead) { playAnimation("death",-1,-1); isdead=true;}}

	
	protected EntityBlock(){/*USED FOR DUMMY*/}
	
	public Entity createByString(MainClass m,String s,String split){
		Entity e = null;
		System.out.println("Initing "+getName());
		try{
		int x = Integer.parseInt(s.split(split)[0]);
		int z = Integer.parseInt(s.split(split)[1]);
		int block = Integer.parseInt(s.split(split)[2]);
		e=new EntityBlock(m,x,z,block);
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
		s=s+(int)this.getBlockID();
		s=s+"\n";
		return s;
	}
	
	public String getTextureName(int i){
		return "blockmove";
	}
	int ticks=0;
	public void addRender(){
		getAnimation().getCurA().addTick();
		if(isDead())ticks++;
	}
	
	public void renderShadow(MainClass m){
		if(isDead() && ticks > 40)m.getWorld().removeEntity(this);
		return;
	}


}
