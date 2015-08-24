package me.engine.skill;

import java.util.Random;

import me.engine.entity.EntityLiving;
import me.engine.entity.Particle;
import me.engine.location.Location;
import me.engine.location.Velocity;
import me.engine.main.MainClass;

public class SkillExplode extends Skill {
	public SkillExplode(){
		super();
	}

	@Override
	public int getCooldown() {
		return 240;
	}
	float range=2f;
	@Override
	public void tick(EntityLiving living, MainClass m) {
		if(living.hasEffect(skillname)==235){
			living.addStatus("UNMOVE", 240, false);
			living.playAnimation("death",-1,80);
		
			
		}
		if(living.hasEffect(skillname)==235-80){
			living.playAnimation("walk",-1,-1);
			explode(m,living.getLocation());
			
			m.getSoundPlayer().playSound("attack", true);
		}
	}
	
	private void hit(Location loc,EntityLiving goal){		
		if(Location.getDistance(loc, goal.getLocation()) < 0.5f){
			goal.damage(1, true);
			goal.addStatus("UNMOVE", 40, true);
		}

	}
	
	Random random = new Random();
	
	public void explode(MainClass m,Location loc){
		for(int x=-5;x<5;x++)
			for(int z=-5;z<5;z++){
				if(Location.getDistance(loc.add(new Location(random.nextInt(4)-2,random.nextInt(4)-2)), new Location(loc.x+x,loc.z+z))<=range){
					Location loc2 = new Location(loc.getX()+x,loc.getZ()+z);
					m.getWorld().addParticle(new Particle(loc2.x,loc2.z,50+random.nextInt(80),new Velocity(0,0),6));
					hit(loc2, m.getWorld().getPlayer());
					for (int a = 0; a < m.getWorld().getEntityArray().length; a++) {
						if (m.getWorld().getEntityArray()[a] == null)
							continue;
						if (m.getWorld().getEntityArray()[a] instanceof EntityLiving) {
							EntityLiving el = (EntityLiving) m
									.getWorld().getEntityArray()[a];
							hit(loc2, el);
						}
					}
				}
			}
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
		return "Explode";
	}

}
