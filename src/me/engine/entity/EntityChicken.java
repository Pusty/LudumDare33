package me.engine.entity;


import java.util.Random;

import me.engine.location.Location;
import me.engine.main.MainClass;
import me.engine.skill.SkillBloodball;

public class EntityChicken extends EntityMonster {
	public EntityChicken(MainClass m, int x, int y) {
		super(m, x, y, "chicken");
//		setSkill(0,new SkillBloodball());
	}

	public String getDialogText(Random r) {
		int random = r.nextInt(3);
		if (random == 0)
			return "I am a chicken";
		if (random == 1)
			return "cluck cluck";
		if (random == 2)
			return "CLUUUCK!";
		return null;
	}
	@Override
	public int getStartHealth(){return 1;}
	public Entity createByString(MainClass m, String s, String split) {
		Entity e = null;
		System.out.println("Initing " + getName());
		try {
			int x = Integer.parseInt(s.split(split)[0]);
			int z = Integer.parseInt(s.split(split)[1]);
			e = new EntityChicken(m, x, z);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}

	public String createToString(MainClass m, String split) {
		String s = "";
		s = s + (int) this.getX();
		s = s + split;
		s = s + (int) this.getZ();
		s = s + "\n";
		return s;
	}


	public void tick(MainClass m){
//			addStatus("SKILL1",999999,false);
		super.tick(m);
	}
	protected EntityChicken() {/* USED FOR DUMMY */
	}

	public String getName() {
		return "Chicken";
	}

	
	Random random = new Random();


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
	
	public void addRender(){
		if(getAnimation().currentanimation != 0 || moving)
		getAnimation().getCurA().addTick();
	}

	public String getTextureName(int i){
		return "chicken";
	}
	public void render(MainClass m){
		super.render(m);
	}
	
	
}
