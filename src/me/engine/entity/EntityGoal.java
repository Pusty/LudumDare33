package me.engine.entity;


import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;

import me.engine.main.MainClass;

import org.lwjgl.opengl.GL11;

public class EntityGoal extends Entity{
	boolean hit=false;
	MainClass main;
	public EntityGoal(MainClass m,float x, float y) {
		super(x, y, 1f,1f);
		main=m;

	}	
	public boolean isHit(){return hit;}
	public void setHit(boolean b){if(hit==false && b==true)main.getSavedData().putData("block", (int)main.getSavedData().getData("block")+1);hit=b;}
	
	protected EntityGoal(){/*USED FOR DUMMY*/}
	
	public Entity createByString(MainClass m,String s,String split){
		Entity e = null;
		System.out.println("Initing "+getName());
		try{
		int x = Integer.parseInt(s.split(split)[0]);
		int z = Integer.parseInt(s.split(split)[1]);
		e=new EntityGoal(m,x,z);
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
	

	public void addRender(){
	}
	
	public void playAnimation(int ind,int f,int s){
	}
	public void playAnimation(String name,int f,int s){
	}
	public String getRenderID(){
		return "goal";
	}
	
	

	public void render(MainClass m) {
		GL11.glPushMatrix();
		GL11.glTranslatef(this.getX(), this.getZ(), 0);
		if(!isHit())
		glBindTexture(GL_TEXTURE_2D,m.getPictureLoader().getImageAsInteger("goal"));
		else
			glBindTexture(GL_TEXTURE_2D,m.getPictureLoader().getImageAsInteger("goal_hit"));
		glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0f, 1f);
		GL11.glVertex2f(0,1f);
		GL11.glTexCoord2f(1f, 1f);
		GL11.glVertex2f(1f,1f);
		GL11.glTexCoord2f(1f, 0f);
		GL11.glVertex2f(1f,0f);
		GL11.glTexCoord2f(0f, 0f);
		GL11.glVertex2f(0f,0f);	
		GL11.glEnd();
		

		
	
		GL11.glPopMatrix();
	}
}
