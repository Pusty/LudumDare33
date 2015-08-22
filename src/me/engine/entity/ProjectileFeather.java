package me.engine.entity;

import me.engine.location.Velocity;
import me.engine.main.MainClass;

import me.engine.render.Animation;
import me.engine.skill.Skill;


public class ProjectileFeather extends Projectile{
	int maxticks=0;
	public ProjectileFeather(MainClass m,float x, float y,Velocity v,Skill skill) {
		super(m,x,y,v,skill);
		if(v.getX() > 0)
		this.playAnimation(0, 2, -1);
		else if(v.getX() < 0)
		this.playAnimation(0, 3, -1);
		else if(v.getZ() > 0)
		this.playAnimation(0, 0, -1);
		else if(v.getZ() < 0)
		this.playAnimation(0, 1, -1);
		
		maxticks=10;
	}	
	protected ProjectileFeather(){/*USED FOR DUMMY*/}
	
	public Entity createByString(MainClass m,String s,String split){
		Entity e = null;
		System.out.println("Initing "+getName());
		try{
		int x = Integer.parseInt(s.split(split)[0]);
		int z = Integer.parseInt(s.split(split)[1]);
		Velocity v = new Velocity(Float.parseFloat(s.split(split)[2]),Float.parseFloat(s.split(split)[3]));
		e=new ProjectileFeather(m,x,z,v,null);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return e;
	}
	public  String getName(){return "ProjectileFeather";}
	

	public String getTextureName(int i){return "feather";}

	public void tick(MainClass m){
		if(maxticks>0)
		maxticks--;
		else
		m.getWorld().removeEntity(this);
	}
	public void hit(MainClass m){
		m.getWorld().addParticle(new Particle(getX(),getZ(),80,new Velocity(0,0),4));
	}
	public void addRender(){

	}
	
	
}
