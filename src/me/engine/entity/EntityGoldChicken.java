package me.engine.entity;


import java.util.Random;

import me.engine.location.Location;
import me.engine.main.Inventory;
import me.engine.main.MainClass;
import me.engine.skill.SkillBloodball;
import me.engine.skill.SkillFeather;

public class EntityGoldChicken extends EntityMonster {
	public EntityGoldChicken(MainClass m, float x, float y) {
		super(m, x, y, "goldchicken");
		setSkill(0,new SkillFeather());
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
	public int getStartHealth(){return 5;}
	public Entity createByString(MainClass m, String s, String split) {
		Entity e = null;
		System.out.println("Initing " + getName());
		try {
			int x = Integer.parseInt(s.split(split)[0]);
			int z = Integer.parseInt(s.split(split)[1]);
			e = new EntityGoldChicken(m, x, z);
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


	public void death(MainClass m){
		int in = (int)m.getSavedData().getData("chicken");
		in++;
		m.getSavedData().putData("chicken",in);
		m.getWorld().getPlayer().setSkill(0, Inventory.skillByIndex(5));
		
		if(m.getWorld().getBoss()!=null)
			m.getWorld().getBoss().damage(1, false);
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
	protected EntityGoldChicken() {/* USED FOR DUMMY */
	}

	public String getName() {
		return "GoldChicken";
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
		return "goldchicken";
	}
	public void render(MainClass m){
		super.render(m);
	}
	
	
}
