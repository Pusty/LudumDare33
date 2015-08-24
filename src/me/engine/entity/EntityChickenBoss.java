package me.engine.entity;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import me.engine.location.Location;
import me.engine.main.MainClass;
import me.engine.skill.SkillSummon;

public class EntityChickenBoss extends EntityMonster {
Location spawn;
	public EntityChickenBoss(MainClass m, float x, float y) {
		super(m, x, y, "chicken");
		setSkill(0,new SkillSummon());
		spawn = new Location(x,y);
	}

	public String getDialogText(Random r) {
		return "IM A CHICKEN CHUCK CHUCK CHUCK!";
	}
	@Override
	public int getStartHealth(){return 15;}
	
	public int damage(int i,boolean b) {
		int start = getHealth();
		if(getHealth()<=0)return 0;
		if(i>0)	main.getSoundPlayer().playSound("death", true);
		if(i>0 && this.hasEffect("DMG")>=0)return start;
		if(i>0 && !b){
			if(getHealth()-i>0)setHealth(this.getHealth()-i);
			else setHealth(0);
		}
		if(start>getHealth())this.addStatus("DMG",Status.DAMAGE_COOLDOWN,false);
		
		return getHealth();
	}
	
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

	public void death(MainClass m){
		System.out.println("Boss finished");
	}
	public void tick(MainClass m){
		addStatus("SKILL1",999999,false);
		
		super.tick(m);
	}
	protected EntityChickenBoss() {/* USED FOR DUMMY */
	}

	public String getName() {
		return "ChickenBoss";
	}

	
	Random random = new Random();


	public void recalcNextLocation(boolean b) {
		times++;
		timer++;
		if(timer<=40 && !b)return;
		timer=0;
		if (nextlocation == null || times > 10){
			nextlocation =spawn
					.add(new Location(random.nextFloat()*15f - 7.5f, random.nextFloat()*15f - 7.5f))
					.clone();
			times=0;
		}
		if (Location.getDistance(this.getLocation(), nextlocation) < 0.5f) {
			nextlocation = spawn
					.add(new Location(random.nextFloat()*15 - 7.5f, random.nextFloat()*15f - 7.5f))
					.clone();

		}
	}
	
	public void addRender(){
		if(getAnimation().currentanimation != 0 || moving)
		getAnimation().getCurA().addTick();
	}

	float size=3f;
	
	public void render(MainClass m){
		GL11.glPushMatrix();
		GL11.glTranslatef(location.x+size, location.z+size, 0);
		glBindTexture(GL_TEXTURE_2D,
		m.getPictureLoader().getImageAsInteger(getRenderID()));
		glBegin(GL_QUADS);
		GL11.glTexCoord2f(0f, 1f);
		GL11.glVertex2f(-size,size);
		GL11.glTexCoord2f(1f, 1f);
		GL11.glVertex2f(size,size);
		GL11.glTexCoord2f(1f, 0f);
		GL11.glVertex2f(size,-size);
		GL11.glTexCoord2f(0f, 0f);
		GL11.glVertex2f(-size,-size);	
		glEnd(); 
		GL11.glPopMatrix();
	}

	

	public void renderShadow(MainClass m){
		GL11.glPushMatrix();
		GL11.glTranslatef(this.getX()+size, this.getZ()+size, 0);
		GL11.glTranslatef(0, -0.15f * size*2f, 0f);
		glBindTexture(GL_TEXTURE_2D,m.getPictureLoader().getImageAsInteger("shadow"));
		glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0f, 1f);
		GL11.glVertex2f(-size,size);
		GL11.glTexCoord2f(1f, 1f);
		GL11.glVertex2f(size,size);
		GL11.glTexCoord2f(1f, 0f);
		GL11.glVertex2f(size,-size);
		GL11.glTexCoord2f(0f, 0f);
		GL11.glVertex2f(-size,-size);	
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	
	
	public String getTextureName(int i){
		return "chicken";
	}

}
