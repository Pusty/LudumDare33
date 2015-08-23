package me.engine.skill;

import me.engine.entity.EntityGoal;
import me.engine.entity.EntityLiving;
import me.engine.entity.Particle;
import me.engine.entity.Projectile;
import me.engine.location.Location;
import me.engine.location.Velocity;
import me.engine.main.MainClass;

public abstract class Skill {
	protected int[] skillproj;
	protected Location[] skilllocs;
	protected String skillname;
	public EntityLiving sender;
	public Skill(){
	}
	public abstract int getCooldown();
	public abstract String getName();
	public abstract void tick(EntityLiving living, MainClass m);
	public void startSkill(EntityLiving living,MainClass m, String string){
		skillname=string;
		reset(living,m);
	}
	public abstract void reset(EntityLiving living,MainClass m);
	public abstract void end(EntityLiving living,MainClass m);
	public boolean projectileHit(MainClass m,Projectile p,EntityLiving hit){
		return false;
	}
	
	public boolean projectileHitGoal(MainClass m,Projectile p,EntityGoal hit){
		if(Location.getDistance(p.getLocation(), hit.getLocation()) < 1f && hit.isHit()==false){
			hit.setHit(true);
			m.getWorld().addParticle(new Particle(hit.getX(),hit.getZ(),80,new Velocity(0,0),1));
			m.getSoundPlayer().playSound("hit", true);
		return true;
		}
		return false;
	}
}
