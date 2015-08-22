package spy.gui.features;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import me.engine.location.Location;
import me.engine.main.MainClass;
import me.engine.render.Render2D;
import spy.gui.*;
import spy.gui.features.*;

public class SkillInventory extends IPanel
{

	public SkillInventory(Location loc) 
	{
		super(loc,new Location(10,2),"",20,80,20,80);

	}
	
	public void PaintBackground()
	{
//		Render2D.setColor(m_backColor.getRed(), m_backColor.getGreen(), m_backColor.getBlue());
//		Render2D.FillBox(GetAbsoluteLocation().x, GetAbsoluteLocation().z, GetAbsoluteSize().x, GetAbsoluteSize().z);

//		Render2D.setColor(m_foreColor.getRed(), m_foreColor.getGreen(), m_foreColor.getBlue());
//		Render2D.DrawBox(GetAbsoluteLocation().x, GetAbsoluteLocation().z, GetAbsoluteSize().x, GetAbsoluteSize().z);
	}
	
	int timer=0;
	int buttonIndex=0;
	int skillIndex=0;
	int offset=0;

	public void OnClick(MainClass m,float mx,float mz) {
		if(timer>=0){}
		else{
		buttonClick(m,mx,mz);
		timer=20;
		}
	}
	public int getItemIndex(){
		return skillIndex+offset;
	}
	public void tick(){
		if(timer>=0)timer--;
	}
	public void buttonClick(MainClass m,float mx,float mz){
		int value = (int) ((mz-this.getP3()) / 10);
		buttonIndex=4-value;
		if(buttonIndex >= 0 && buttonIndex < 4)
			skillIndex=buttonIndex;
		
		if(buttonIndex == -1){
			if(offset > 0){
			offset=offset-1;
			skillIndex=skillIndex+1;
			}
		}if(buttonIndex == 4){
			String skills = (String) m.getSavedData().getData("inv");
			String[] inv = skills.split("%");
			if(offset < inv.length-4){
			offset=offset+1;
			skillIndex=skillIndex-1;
			}
		}
	}

	public void Paint()
	{
		String skills = (String) MainClass.classForRender.getSavedData().getData("inv");
		String[] inv = skills.split("%");
		String[] invS = new String[inv.length];
		for(int in=0;in<inv.length;in++){
			if(inv[in].split("&").length > 1)
			invS[in]=inv[in].split("&")[1];
		}
		
		String[] show = new String[4];
		
		for(int in=0;in<4;in++){
			if(in+offset < invS.length && invS[in+offset] != null){
				show[in]=invS[in+offset];
			}else{show[in]="None";}
		}
		
		
		for(int i=0;i<4;i++){
			renderButton(MainClass.classForRender,i,i==skillIndex,show[i]);
		}
		renderButton(MainClass.classForRender,-1,-1==buttonIndex,"///");
		renderButton(MainClass.classForRender,4,4==buttonIndex,"///");
	
	}

	public void LeftMouseDown(Mouse mouse)
	{
		if(mouse.At(GetAbsoluteLocation(), GetAbsoluteSize()))
		{
			m_eInnerState = InnerMouseState_e.Pressed;
			UpdateColors();
		}
	}	
	public void LeftMouseUp(Mouse mouse)
	{
		m_eInnerState= mouse.At(GetAbsoluteLocation(), GetAbsoluteSize()) ? InnerMouseState_e.Hover : InnerMouseState_e.None;
		UpdateColors();
	}
	
	public void RightMouseDown(Mouse mouse)
	{
		
	}
	
	public void RightMouseUp(Mouse mouse)
	{
		
	}
	
	public void MouseMove(Mouse mouse)
	{
		if(mouse.At(GetAbsoluteLocation(),GetAbsoluteSize()) && m_eInnerState != InnerMouseState_e.Pressed)
		{
			m_eInnerState = InnerMouseState_e.Hover;
			UpdateColors();
		}
		else
		{
			m_eInnerState = InnerMouseState_e.None;
			UpdateColors();
		}
	}


	
	
	public void UpdateColors()
	{
		if(m_eInnerState == InnerMouseState_e.None)
		{
			m_backColor = new Color(100,100,100,255);
			m_foreColor = new Color(255,255,255,255);
		}
		if(m_eInnerState == InnerMouseState_e.Hover)
		{
			m_backColor = new Color(150,150,150,255);
			m_foreColor = new Color(255,255,255,255);
		}
		if(m_eInnerState == InnerMouseState_e.Pressed)
		{
			m_backColor = new Color(200,200,200,255);
			m_foreColor = new Color(80,80,80,255);
		}
	}
	
	protected Color m_foreColor;
	protected Color m_backColor;
	protected InnerMouseState_e m_eInnerState;
	
	public void renderButton(MainClass m,int i,boolean b,String text){
		GL11.glPushMatrix();
		GL11.glTranslatef(m_location.x+0.5f, m_location.z+0.5f, 0f);
		GL11.glTranslatef(0f, -2f*i, 0);
		if((b && i >= 0 && i < 4) || ((i==-1 || i==4 ) && b && timer > 0))
			glBindTexture(GL_TEXTURE_2D,
					m.getPictureLoader().getImageAsInteger("item_8"));
		else
			glBindTexture(GL_TEXTURE_2D,
					m.getPictureLoader().getImageAsInteger("item_0"));
		glBegin(GL_QUADS);
		GL11.glTexCoord2f(0f, 1f);
		GL11.glVertex2f(0f,m_size.z);
		GL11.glTexCoord2f(1f, 1f);
		GL11.glVertex2f(m_size.x,m_size.z);
		GL11.glTexCoord2f(1f, 0f);
		GL11.glVertex2f(m_size.x,0f);
		GL11.glTexCoord2f(0f, 0f);
		GL11.glVertex2f(0f,0f);	
		glEnd(); 

		GL11.glTranslatef(5f- text.length()/2f,0.5f,0f);
		Render2D.renderString(m, text);
		GL11.glPopMatrix();
	}
}


