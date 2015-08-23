package me.engine.skill;

import java.util.Random;

import me.engine.entity.EntityBombChicken;
import me.engine.entity.EntityChicken;
import me.engine.entity.EntityGoldChicken;
import me.engine.entity.EntityLiving;
import me.engine.entity.EntitySummon;
import me.engine.location.Location;
import me.engine.location.Velocity;
import me.engine.main.MainClass;

public class SkillSummon extends Skill {
	public SkillSummon(){
		super();
	}

	@Override
	public int getCooldown() {
		return 1000;
	}
	
	
	Random random = new Random();
	@Override
	public void tick(EntityLiving living, MainClass m) {
		if(living.hasEffect(skillname)==1000-10){
			living.addStatus("UNMOVE", 360, false);
			living.playAnimation("attack",-1,360);
		}
		if(living.hasEffect(skillname)==1000-360){
			Velocity v= new Velocity(0,0);
			if(living.getSide()==0)v=new Velocity(0,-0.5f/20);
			if(living.getSide()==1)v=new Velocity(0,0.5f/20);
			if(living.getSide()==2)v=new Velocity(0.5f/20,0);
			if(living.getSide()==3)v=new Velocity(-0.5f/20,0);
			int index = random.nextInt(5);
			if(index == 0)
				skillproj[0]=m.getWorld().addEntity(new EntityChicken(m,living.getX()+v.x/2f,living.getZ()+v.z/2f));
			else if(index == 1 || index == 2)
				skillproj[0]=m.getWorld().addEntity(new EntityGoldChicken(m,living.getX()+v.x/2f,living.getZ()+v.z/2f));
			else if(index == 3 || index == 4)
				skillproj[0]=m.getWorld().addEntity(new EntityBombChicken(m,living.getX()+v.x/2f,living.getZ()+v.z/2f));
			
			m.getSoundPlayer().playSound("attack", true);
		}
	}

	@Override
	public void reset(EntityLiving living,MainClass m) {
		this.skilllocs=new Location[1];
		this.skillproj=new int[1];
		this.skilllocs[0]=living.getLocation().clone();
		this.sender=living;
	}

	@Override
	public void end(EntityLiving living,MainClass m) {
	}

	@Override
	public String getName() {
		return "Fireball";
	}
}
