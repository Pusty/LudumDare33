package me.engine.entity;

import java.util.Random;

import me.engine.location.Location;
import me.engine.main.Inventory;
import me.engine.main.MainClass;
import me.engine.skill.SkillFireball;



public class EntityHuman extends EntityMonster {
	public EntityHuman(MainClass m,int x, int y) {
		super(m ,x, y,"human");
		setSkill(0,new SkillFireball());
	}

	
	public String getDialogText(Random r){
		int random = r.nextInt(3);
		if(random==0)
			return "I like cheese!";
		if(random==1)
			return "I would love to see a magic cheese at some point";
		if(random==2)
			return "Have you seen the cheese dungeon yet?";
		return null;
	}
	
	public Entity createByString(MainClass m,String s,String split){
		Entity e = null;
		System.out.println("Initing "+getName());
		try{
		int x = Integer.parseInt(s.split(split)[0]);
		int z = Integer.parseInt(s.split(split)[1]);
		e=new EntityHuman(m,x,z);
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
	public void death(MainClass m){
		if((int)m.getSavedData().getData("chicken") > 0)
		m.getWorld().getPlayer().setSkill(0, Inventory.skillByIndex(0));
	}
	public float getSpeed(){return 0.0125f;}
	
	protected EntityHuman(){/*USED FOR DUMMY*/}
	public  String getName(){return "Human";}
	
	public void addRender(){
		if(getAnimation().currentanimation != 0 || moving)
			getAnimation().getCurA().addTick();
	}
	
	public String getTextureName(int i){
		return "player1";
	}

	@Override
	public int getStartHealth(){return 3;}

	public void recalcNextLocation(boolean b) {
		times++;
		timer++;
		if(timer<=40 && !b)return;
		timer=0;
		if (nextlocation == null || times > 10){
			nextlocation = main.getWorld().getPlayer().getLocation()
					.add(new Location(random.nextFloat(), random.nextFloat()))
					.clone();
			times=0;
		}
		if (Location.getDistance(this.getLocation(), nextlocation) < 0.5f) {
			nextlocation = main.getWorld().getPlayer().getLocation()
					.add(new Location(random.nextFloat(), random.nextFloat()))
					.clone();

		}
	}
	
	public void tick(MainClass m){
		Location player  = m.getWorld().getPlayer().getLocation();
		if(Math.abs(player.x - this.getX()) < 2f || Math.abs(player.z - this.getZ()) < 2f ){
			if((player.x - this.getX() < 0 && player.x - this.getX() > -2f && side==0)
				|| (player.x - this.getX() > 0 && player.x - this.getX() < 2f && side==1)
				|| (player.z - this.getZ() < 0 && player.z - this.getX() > -2f && side==3)
				|| (player.z - this.getZ() > 0 && player.z - this.getZ() < 2f && side==2)
				)
			addStatus("SKILL1",999999,false);
		}
		
		super.tick(m);
	}


}
