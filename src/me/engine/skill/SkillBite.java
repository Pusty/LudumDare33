package me.engine.skill;

import me.engine.entity.EntityChicken;
import me.engine.entity.EntityLiving;
import me.engine.entity.EntitySummon;
import me.engine.entity.Particle;
import me.engine.location.Location;
import me.engine.location.Velocity;
import me.engine.main.MainClass;

public class SkillBite extends Skill {
	public SkillBite(){
		super();
	}

	@Override
	public int getCooldown() {
		return 240;
	}
	float range=2f;
	boolean hit=false;
	EntityLiving hitting=null;
	@Override
	public void tick(EntityLiving living, MainClass m) {
		if(living.hasEffect(skillname)==235){
			living.addStatus("UNMOVE", 120, false);
			living.playAnimation("attack",-1,120);
			
			m.getWorld().addParticle(new Particle(living.getX(),living.getZ(),80,new Velocity(0,0),3));
			hit=false;
		}
		
		if(living.hasEffect(skillname)==235-80){
			living.playAnimation("walk",-1,-1);
			if(!hit(m,living, m.getWorld().getPlayer()))
				for (int a = 0; a < m.getWorld().getEntityArray().length; a++) {
					if (m.getWorld().getEntityArray()[a] == null)
						continue;
					if (m.getWorld().getEntityArray()[a] instanceof EntityLiving) {
						EntityLiving el = (EntityLiving) m
								.getWorld().getEntityArray()[a];
						if(hit(m,living, el))break;
					}
				}
			
			if(!hit){
				Velocity v= new Velocity(0,0);
				if(living.getSide()==0)v=new Velocity(0,-0.5f/20);
				if(living.getSide()==1)v=new Velocity(0,0.5f/20);
				if(living.getSide()==2)v=new Velocity(0.5f/20,0);
				if(living.getSide()==3)v=new Velocity(-0.5f/20,0);
				m.getWorld().addParticle(new Particle(living.getX(),living.getZ(),40,v.multiplz(0.05f),5));
			}else{
				if(hitting instanceof EntityChicken)
				living.damage(-1, false);
			}
			
			//SOUND HERE
		}
	}
	
	private boolean  hit(MainClass m,EntityLiving at,EntityLiving goal){
		if(at.equals(goal))return false;
		if(goal.getName().equalsIgnoreCase(at.getName()))return false;
		if(goal.isDead())return false;
		
		if(Location.getDistance(at.getLocation(), goal.getLocation()) < range){
			goal.damage(2, true);
			goal.addStatus("UNMOVE", 120, true);
			m.getWorld().addParticle(new Particle(goal.getX(),goal.getZ(),80,new Velocity(0,0),5));
			hit=true;
			hitting=goal;
			return true;
			
		}
		return false;

	}

	@Override
	public void reset(EntityLiving living,MainClass m) {
		this.skilllocs=new Location[1];
		this.skilllocs[0]=living.getLocation().clone();
		this.sender=living;
	}

	@Override
	public void end(EntityLiving living,MainClass m) {
	}

	@Override
	public String getName() {
		return "Bite";
	}
}
