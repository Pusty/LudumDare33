package me.game.main;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import me.engine.location.Location;
import me.engine.main.Controls;
import me.engine.main.DialogClass;
import me.engine.main.GameTickHandler;
import me.engine.main.Inventory;
import me.engine.main.MainClass;
import me.engine.multiplayer.DataClient;
import me.engine.block.HandledBlock;
import me.engine.entity.Player;
import me.engine.gui.Button;
import me.engine.gui.GuiScreen;
import me.engine.gui.SkillInventory;
import me.engine.world.LevelScript;
import me.engine.world.World;
import me.engine.render.AnimationHandler;
import me.engine.render.Render2D;
import me.engine.render.SheetLoader;
import me.game.leveldesign.Level01;
import me.game.leveldesign.Level02;
import me.game.leveldesign.Level03;
import me.game.leveldesign.Level04;
import me.game.leveldesign.Level05;
import me.game.leveldesign.Level06;
import me.game.leveldesign.Level07;
import me.game.leveldesign.Level08;
import me.game.startscreen.StartScreen;

public class StartClass extends MainClass {
	public static boolean light=false;
	public static boolean online=false;
	public void preInit() {
		SheetLoader sheetloader;
		try {

			for (int i = 0; i < 5; i++) {
				try {
					String folder = "player_" + i;
					sheetloader = new SheetLoader(
							System.getProperty("user.dir") + StartScreen.fileThing+"img"+StartScreen.fileThing + folder
									+ StartScreen.fileThing+"player.png", 1, 4 * 4, 16, 16);
					for (int a = 0; a < 4 * 4; a++) {
						getPictureLoader().ImportFromSheet(
								"player" + i + "_" + String.valueOf(a),
								sheetloader, a, 0);
					}
					sheetloader = new SheetLoader(
							System.getProperty("user.dir") + StartScreen.fileThing+"img"+StartScreen.fileThing + folder
									+ StartScreen.fileThing+"player_death.png", 1, 4 * 4, 16, 16);
					for (int a = 0; a < 4 * 4; a++) {
						getPictureLoader().ImportFromSheet(
								"player" + i + "_death_" + String.valueOf(a),
								sheetloader, a, 0);
					}
					sheetloader = new SheetLoader(
								System.getProperty("user.dir") + StartScreen.fileThing+"img"+StartScreen.fileThing + folder
									+ StartScreen.fileThing+"player_attack.png", 1, 4 * 4, 16, 16);
					for (int a = 0; a < 4 * 4; a++) {
						getPictureLoader().ImportFromSheet(
								"player" + i + "_attack_" + String.valueOf(a),
								sheetloader, a, 0);
					}
					sheetloader = new SheetLoader(
								System.getProperty("user.dir") + StartScreen.fileThing+"img"+StartScreen.fileThing + folder
									+ StartScreen.fileThing+"player_melee.png", 1, 4 * 4, 16, 16);
					for (int a = 0; a < 4 * 4; a++) {
						getPictureLoader().ImportFromSheet(
								"player" + i + "_melee_" + String.valueOf(a),
								sheetloader, a, 0);
					}
					System.out.println("Found Player " + i);
				} catch (Exception e) {
				}
			}

			try {
				String folder = "slime";
				sheetloader = new SheetLoader(System.getProperty("user.dir")
						+ StartScreen.fileThing+"img"+StartScreen.fileThing + folder + StartScreen.fileThing+"slime.png", 1, 4 * 4, 16, 16);
				for (int a = 0; a < 4 * 4; a++) {
					getPictureLoader()
							.ImportFromSheet(folder + "_" + String.valueOf(a),
									sheetloader, a, 0);
				}
				sheetloader = new SheetLoader(System.getProperty("user.dir")
						+ StartScreen.fileThing+"img"+StartScreen.fileThing + folder + StartScreen.fileThing+"slime_death.png", 1, 4 * 4,
						16, 16);
				for (int a = 0; a < 4 * 4; a++) {
					getPictureLoader().ImportFromSheet(
							folder + "_death_" + String.valueOf(a),
							sheetloader, a, 0);
				}
				System.out.println("Found Slime");
			} catch (Exception e) {
			}
			
			try {
				String folder = "bloodslime";
				sheetloader = new SheetLoader(System.getProperty("user.dir")
						+ StartScreen.fileThing+"img"+StartScreen.fileThing + folder + StartScreen.fileThing+"slime.png", 1, 4 * 4, 16, 16);
				for (int a = 0; a < 4 * 4; a++) {
					getPictureLoader()
							.ImportFromSheet(folder + "_" + String.valueOf(a),
									sheetloader, a, 0);
				}
				sheetloader = new SheetLoader(System.getProperty("user.dir")
						+ StartScreen.fileThing+"img"+StartScreen.fileThing + folder + StartScreen.fileThing+"slime_death.png", 1, 4 * 4,
						16, 16);
				for (int a = 0; a < 4 * 4; a++) {
					getPictureLoader().ImportFromSheet(
							folder + "_death_" + String.valueOf(a),
							sheetloader, a, 0);
				}
				System.out.println("Found BloodSlime");
			} catch (Exception e) {
			}
			
			try {
				String folder = "chicken";
				sheetloader = new SheetLoader(System.getProperty("user.dir")
						+ StartScreen.fileThing+"img"+StartScreen.fileThing + folder + StartScreen.fileThing+"chicken.png", 1, 4 * 4, 16, 16);
				for (int a = 0; a < 4 * 4; a++) {
					getPictureLoader()
							.ImportFromSheet(folder + "_" + String.valueOf(a),
									sheetloader, a, 0);
				}
				sheetloader = new SheetLoader(System.getProperty("user.dir")
						+ StartScreen.fileThing+"img"+StartScreen.fileThing + folder + StartScreen.fileThing+"chicken_death.png", 1, 4 * 4,
						16, 16);
				for (int a = 0; a < 4 * 4; a++) {
					getPictureLoader().ImportFromSheet(
							folder + "_death_" + String.valueOf(a),
							sheetloader, a, 0);
				}
				System.out.println("Found Chicken");
			} catch (Exception e) {
			}
			
			try {
				String folder = "goldchicken";
				sheetloader = new SheetLoader(System.getProperty("user.dir")
						+ StartScreen.fileThing+"img"+StartScreen.fileThing + folder + StartScreen.fileThing+"goldchicken.png", 1, 4 * 4, 16, 16);
				for (int a = 0; a < 4 * 4; a++) {
					getPictureLoader()
							.ImportFromSheet(folder + "_" + String.valueOf(a),
									sheetloader, a, 0);
				}
				sheetloader = new SheetLoader(System.getProperty("user.dir")
						+ StartScreen.fileThing+"img"+StartScreen.fileThing + folder + StartScreen.fileThing+"goldchicken_death.png", 1, 4 * 4,
						16, 16);
				for (int a = 0; a < 4 * 4; a++) {
					getPictureLoader().ImportFromSheet(
							folder + "_death_" + String.valueOf(a),
							sheetloader, a, 0);
				}
				System.out.println("Found Gold Chicken");
			} catch (Exception e) {
			}
			
			
			try {
				String folder = "bombchicken";
				sheetloader = new SheetLoader(System.getProperty("user.dir")
						+ StartScreen.fileThing+"img"+StartScreen.fileThing + folder + StartScreen.fileThing+"bombchicken.png", 1, 4 * 4, 16, 16);
				for (int a = 0; a < 4 * 4; a++) {
					getPictureLoader()
							.ImportFromSheet(folder + "_" + String.valueOf(a),
									sheetloader, a, 0);
				}
				sheetloader = new SheetLoader(System.getProperty("user.dir")
						+ StartScreen.fileThing+"img"+StartScreen.fileThing + folder + StartScreen.fileThing+"bombchicken_death.png", 1, 4 * 4,
						16, 16);
				for (int a = 0; a < 4 * 4; a++) {
					getPictureLoader().ImportFromSheet(
							folder + "_death_" + String.valueOf(a),
							sheetloader, a, 0);
				}
				System.out.println("Found Bomb Chicken");
			} catch (Exception e) {
			}




			sheetloader = new SheetLoader(System.getProperty("user.dir")
					+ StartScreen.fileThing+"img"+StartScreen.fileThing+"items.png", 8, 8, 16, 16);
			for (int a = 0; a < 8 * 8; a++) {
				getPictureLoader().ImportFromSheet("item_" + String.valueOf(a),
						sheetloader, a % 8, a / 8);
			}

			String[] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
					"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
					"V", "W", "X", "Y", "Z", ":", "!", "?", ".", "[", "]", "0",
					"1", "2", "3", "4", "5", "6", "7", "8", "9", "(", ")", "+",
					"-", "/", " ", "_" };
			sheetloader = new SheetLoader(System.getProperty("user.dir")
					+ StartScreen.fileThing+"img"+StartScreen.fileThing+"letters.png", 8, 8, 16, 16);
			for (int a = 0; a < letter.length; a++) {
				getPictureLoader().ImportFromSheet("char_" + letter[a],
						sheetloader, a % 8, a / 8);

			}

			getPictureLoader().addImage("shadow",
					System.getProperty("user.dir") + StartScreen.fileThing+"img"+StartScreen.fileThing+"shadow.png");
			getPictureLoader().addImage("tree",
					System.getProperty("user.dir") + StartScreen.fileThing+"img"+StartScreen.fileThing+"tree.png");
			getPictureLoader().addImage("goal",
					System.getProperty("user.dir") + StartScreen.fileThing+"img"+StartScreen.fileThing+"goal.png");
			getPictureLoader().addImage("goal_hit",
					System.getProperty("user.dir") + StartScreen.fileThing+"img"+StartScreen.fileThing+"goal_hit.png");

			sheetloader = new SheetLoader(System.getProperty("user.dir")
					+ StartScreen.fileThing+"img"+StartScreen.fileThing+"flame.png", 1, 4, 16, 16);
			for (int a = 0; a < 4; a++)
				getPictureLoader().ImportFromSheet("flame_" + a, sheetloader,
						a, 0);
			
			sheetloader = new SheetLoader(System.getProperty("user.dir")
					+ StartScreen.fileThing+"img"+StartScreen.fileThing+"blood.png", 1, 4, 16, 16);
			for (int a = 0; a < 4; a++)
				getPictureLoader().ImportFromSheet("blood_" + a, sheetloader,
						a, 0);
			
			
			sheetloader = new SheetLoader(System.getProperty("user.dir")
					+ StartScreen.fileThing+"img"+StartScreen.fileThing+"feather.png", 1, 4, 16, 16);
			for (int a = 0; a < 4; a++)
				getPictureLoader().ImportFromSheet("feather_" + a, sheetloader,
						a, 0);
			
			
			sheetloader = new SheetLoader(System.getProperty("user.dir")
					+ StartScreen.fileThing+"img"+StartScreen.fileThing+"health.png", 1, 4, 16, 16);
			for (int a = 0; a < 4; a++)
				getPictureLoader().ImportFromSheet("health_" + a, sheetloader,
						a, 0);
			
			sheetloader = new SheetLoader(System.getProperty("user.dir")
					+ StartScreen.fileThing+"img"+StartScreen.fileThing+"blockM.png", 1, 4, 16, 16);
			for (int a = 0; a < 4; a++)
				getPictureLoader().ImportFromSheet("blockmove_" + a, sheetloader,
						a, 0);
			
			sheetloader = new SheetLoader(System.getProperty("user.dir")
					+ StartScreen.fileThing+"img"+StartScreen.fileThing+"blockM_death.png", 1, 4, 16, 16);
			for (int a = 0; a < 4; a++)
				getPictureLoader().ImportFromSheet("blockmove_death_" + a, sheetloader,
						a, 0);
			
			
			sheetloader = new SheetLoader(System.getProperty("user.dir")
					+ StartScreen.fileThing+"img"+StartScreen.fileThing+"portal.png", 1, 4, 16, 16);
			for (int a = 0; a < 4; a++)
				getPictureLoader().ImportFromSheet("portal_" + a, sheetloader,
						a, 0);
			
			for(int n=0;n<10;n++){
				try{
					sheetloader = new SheetLoader(System.getProperty("user.dir")
							+ StartScreen.fileThing+"img"+StartScreen.fileThing+"particle_"+n+".png", 1, 4, 16, 16);
					for (int a = 0; a < 4; a++)
						getPictureLoader().ImportFromSheet("par"+n+"_" + a, sheetloader,
								a, 0);
				}catch(Exception e){};
			}
			
			try {
				Image img = ImageIO.read(new File(System.getProperty("user.dir")
						+ StartScreen.fileThing+"data"+StartScreen.fileThing+"map_" + 0 + ".png"));
				 sheetloader = new SheetLoader(
						System.getProperty("user.dir") + StartScreen.fileThing+"data"+StartScreen.fileThing+"map_" + 0 + ".png",
						1, img.getWidth(null) / 16, 16, 16);
				for (int a = 0; a < (img.getWidth(null) / 16); a++) {
					getPictureLoader().ImportFromSheet(
							"block_" + String.valueOf(a), sheetloader, a, 0);
				}
			} catch (Exception e) {

			}

			
			//Init animations
			AnimationHandler.addHandler("player1", 4);
			AnimationHandler.getHandler("player1").addAnimation("player1", "walk", 100, 1, true);
			AnimationHandler.getHandler("player1").addAnimation("player1_melee", "melee", 160, 0, false);
			AnimationHandler.getHandler("player1").addAnimation("player1_attack", "attack", 160, 0, false);
			AnimationHandler.getHandler("player1").addAnimation("player1_death", "death", 120, 0, false);
			
			AnimationHandler.addHandler("player2", 4);
			AnimationHandler.getHandler("player2").addAnimation("player2", "walk", 100, 1, true);
			AnimationHandler.getHandler("player2").addAnimation("player2_melee", "melee", 160, 0, false);
			AnimationHandler.getHandler("player2").addAnimation("player2_attack", "attack", 160, 0, false);
			AnimationHandler.getHandler("player2").addAnimation("player2_death", "death", 120, 0, false);

			
			AnimationHandler.addHandler("slime", 2);
			AnimationHandler.getHandler("slime").addAnimation("slime", "walk", 120, 1, true);
			AnimationHandler.getHandler("slime").addAnimation("slime_death", "death", 120, 0, false);
			
			AnimationHandler.addHandler("chicken", 2);
			AnimationHandler.getHandler("chicken").addAnimation("chicken", "walk", 120, 1, true);
			AnimationHandler.getHandler("chicken").addAnimation("chicken_death", "death", 120, 0, false);

			AnimationHandler.addHandler("goldchicken", 2);
			AnimationHandler.getHandler("goldchicken").addAnimation("goldchicken", "walk", 120, 1, true);
			AnimationHandler.getHandler("goldchicken").addAnimation("goldchicken_death", "death", 120, 0, false);
			
			AnimationHandler.addHandler("bombchicken", 2);
			AnimationHandler.getHandler("bombchicken").addAnimation("bombchicken", "walk", 120, 1, true);
			AnimationHandler.getHandler("bombchicken").addAnimation("bombchicken_death", "death", 120, 0, false);
			
			AnimationHandler.addHandler("bloodslime", 2);
			AnimationHandler.getHandler("bloodslime").addAnimation("bloodslime", "walk", 120, 1, true);
			AnimationHandler.getHandler("bloodslime").addAnimation("bloodslime_death", "death", 120, 0, false);
			
			AnimationHandler.addHandler("portal", 1);
			AnimationHandler.getHandler("portal").addAnimation("portal", "exist", 120, 1, true);
			
			AnimationHandler.addHandler("flame", 1);
			AnimationHandler.getHandler("flame").addAnimation("flame", "exist", 120, 1, true);
			
			AnimationHandler.addHandler("blood", 1);
			AnimationHandler.getHandler("blood").addAnimation("blood", "exist", 120, 1, true);
			
			AnimationHandler.addHandler("health", 1);
			AnimationHandler.getHandler("health").addAnimation("health", "exist", 120, 1, true);
			
			AnimationHandler.addHandler("feather", 1);
			AnimationHandler.getHandler("feather").addAnimation("feather", "exist", 120, 1, true);
			
			
			AnimationHandler.addHandler("blockmove", 2);
			AnimationHandler.getHandler("blockmove").addAnimation("blockmove", "exist", 120, 1, true);
			AnimationHandler.getHandler("blockmove").addAnimation("blockmove_death", "death", 120, 0, false);
			
			AnimationHandler.addHandler("par0", 1);
			AnimationHandler.getHandler("par0").addAnimation("par0", "exist", 120, 0, true);
			
			AnimationHandler.addHandler("par1", 1);
			AnimationHandler.getHandler("par1").addAnimation("par1", "exist", 120, 0, true);
			
			AnimationHandler.addHandler("par2", 1);
			AnimationHandler.getHandler("par2").addAnimation("par2", "exist", 120, 0, true);
			
			AnimationHandler.addHandler("par3", 1);
			AnimationHandler.getHandler("par3").addAnimation("par3", "exist", 120, 0, true);

			AnimationHandler.addHandler("par4", 1);
			AnimationHandler.getHandler("par4").addAnimation("par4", "exist", 120, 0, true);
			
			AnimationHandler.addHandler("par5", 1);
			AnimationHandler.getHandler("par5").addAnimation("par5", "exist", 120, 0, true);
			
			AnimationHandler.addHandler("par6", 1);
			AnimationHandler.getHandler("par6").addAnimation("par6", "exist", 120, 0, true);

			for (int i = 0; i < 64; i++) {
				HandledBlock b = new HandledBlock(i);
				this.getBlockHandler().addBlock("block_" + i, b, i);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Init() {
	}

	@Override
	public void RenderInit() {
		Render2D render2d = new Render2D(this);
		render2d.startRender();

	}

	@Override
	public void ControlInit() {
		Controls controls = new Controls(this);
		controls.startControls();
	}

	@Override
	public void GameTickInit() {
		GameTickHandler g = new GameTickHandler(this);
		this.setGameTickHandler(g);
		this.getGameTickHandler().startGameTick();
	}


	public void postInit(){
		if(online)
		this.setUpConnection(new DataClient(this,"localhost",25565));
	}
	
	public void load(int mapID) {
		getSoundPlayer().playSound("select", true);
		this.loadMap(100);
		getTextLoader().addIndex();
		this.setTimeRunning(false);

		if(mapID != 9){
			Render2D.chunkList = new int[1];
			Render2D.chunkList[0] = 0;

		File f = new File(System.getProperty("user.dir") + StartScreen.fileThing+"data"+StartScreen.fileThing+"map_" + mapID
				+ ".txm");
		File f2 = new File(System.getProperty("user.dir") + StartScreen.fileThing+"data"+StartScreen.fileThing+"smap_" + mapID
				+ ".txm");
		
		LevelScript levelscript = LevelScript.getLevel(mapID);
		levelscript.init(this);
		String line = "";
		try {
			if (!f.exists() || !f2.exists()) {
				System.out.println("File does not exist");
			} else {
				World world = null;
				Location player = new Location(0, 0);
				{
					FileInputStream fi = new FileInputStream(f);
					InputStreamReader isr = new InputStreamReader(fi);
					BufferedReader br = new BufferedReader(isr);
					line = br.readLine();
//					int index = 0;
					int se = 40;
					List<int[]> blockarray = new ArrayList<int[]>();
					while (line != null) {
						int blocks = line.split(",").length;
						int[] intarray = new int[blocks];
						for (int i = 0; i < blocks; i++)
							intarray[i] = Integer.parseInt(line.split(",")[i]);
						blockarray.add(intarray);
						line = br.readLine();
					}
					world = new World(this, blockarray.get(0).length,
							blockarray.size(), se);
					int sz = 0;
					for (int[] ints : blockarray) {
						int sx = 0;
						for (int in : ints) {
							world.setBlockID(sx, blockarray.size() - sz - 1, in);
							sx++;
						}
						sz++;
					}
				}
				
				{	//Meta File
					FileInputStream fi = new FileInputStream(f2);
					InputStreamReader isr = new InputStreamReader(fi);
					BufferedReader br = new BufferedReader(isr);
					line = br.readLine();
					int z=0;
					while (line != null) {
						int blocks = line.split(",").length;
						int x=0;
						for(int index=0;index<blocks;index++){
							int metaID = Integer.parseInt(line.split(",")[index]);
							world.setBlockMeta(x,world.getSizeZ()-z-1, metaID);
							if(metaID-7 == mapID)
								player = new Location(x,world.getSizeZ()-z-1);
							else if(metaID == 3)
								levelscript.addTree(this,world,metaID,x,world.getSizeZ()-z-1);	
							else if(metaID == 4)
								levelscript.addNPC(this,world,metaID,x,world.getSizeZ()-z-1);
//								world.addEntity(new NPCEntity(this,x,world.getSizeZ()-z,"player3"));
							else if(metaID == 5)
								levelscript.addEnemy(this,world,metaID,x,world.getSizeZ()-z-1);
							else if(metaID == 6)
								levelscript.addSpecial1(this,world,metaID,x,world.getSizeZ()-z-1);
							else if(metaID == 7)
								levelscript.addSpecial2(this,world,metaID,x,world.getSizeZ()-z-1);
							else if(metaID > 7)
								levelscript.addPortal(this,world,metaID,x,world.getSizeZ()-z-1);
							
								x++;
						}
						line = br.readLine();
						z++;
					}
				}
				if(getSavedData().getData("world") == getSavedData().getData("worldOld") && (float)getSavedData().getData("posX") >= 0){
					player = new Location((float)getSavedData().getData("posX"),(float)getSavedData().getData("posZ"));
				}else{
					getSavedData().putData("block",0);
				}
				getSavedData().putData("worldOld",(int)getSavedData().getData("world"));
				getSavedData().putData("posX",player.x);
				getSavedData().putData("posZ",player.z);
				world.setPlayer(new Player(this, player.x,  player.z));
				world.getPlayer().setHealth((int)getSavedData().getData("health"));
//				world.calcLight();
				this.setWorld(world);
				reRender(world);
				rerender = true;
				Render2D.changeMusic(this,mapID);
			}

		} catch (Exception e) {
			System.out.println(line);
			e.printStackTrace();
		}
		this.setTimeRunning(true);
		getSavedData().saveToFile("player.txt");
		DialogClass.setDialog(this, mapID);
		this.setLoaded(true);
		
		}
		
	}

	public static boolean rerender = false;

	private void reRender(World w) {
		Render2D.chunkList = new int[w.getChunkArray().length];
		for (int i = 0; i < w.getChunkArray().length; i++)
			Render2D.chunkList[i] = -1;

	}
	public static void openInventory(MainClass m){
		if(m.getGui() != null)return;
		 GuiScreen gui = new GuiScreen(3);	
		gui.setGuiPart(0, new Button(new Location(6,-10),6,2,0,18,60,100,"Exit"){
			@Override
			public void buttonClick(MainClass m,float mx,float mz){
				Controls.close(m);
			}
		});
		gui.setGuiPart(1, new SkillInventory(new Location(-4.5f,1.75f)));
		
		gui.setGuiPart(2, new Button(new Location(-12,-10),6,2,0,18,0,40,"Use"){
			@Override
			public void buttonClick(MainClass m,float mx,float mz){
				Inventory.useItem(m, ((SkillInventory)(m.getGui().getGuiPart(1))).getItemIndex());
			}
		});
		m.setGui(gui);
	}
	public static void closeInventory(MainClass m){m.setGui(null);}
	@Override
	public void WorldInit() {
		LevelScript.addLevel(1, new Level01());
		LevelScript.addLevel(2, new Level02());
		LevelScript.addLevel(3, new Level03());
		LevelScript.addLevel(4, new Level04());
		LevelScript.addLevel(5, new Level05());
		LevelScript.addLevel(6, new Level06());
		LevelScript.addLevel(7, new Level07());
		LevelScript.addLevel(8, new Level08());
	
		getTextLoader().loadFromFile("loading.txt");
		if(!this.getLoadGame())
		getSavedData().loadFromFile("player.txt");
		else
		getSavedData().createFromNew();
		

		load((int)getSavedData().getData("world"));


	}

	@Override
	public void SoundInit() {

	getSoundPlayer().addToBuffer("bg1_long",System.getProperty("user.dir") + StartScreen.fileThing+"util"+StartScreen.fileThing+"game_1_long.wav", true,0.2f);
	getSoundPlayer().addToBuffer("bg2_long",System.getProperty("user.dir") + StartScreen.fileThing+"util"+StartScreen.fileThing+"game_2_long.wav", true,0.2f);
	getSoundPlayer().addToBuffer("bg3_long",System.getProperty("user.dir") + StartScreen.fileThing+"util"+StartScreen.fileThing+"game_3_long.wav", true,0.2f);

	getSoundPlayer().addToBuffer("exp",System.getProperty("user.dir") + StartScreen.fileThing+"util"+StartScreen.fileThing+"exp.wav", false,1f);
	getSoundPlayer().addToBuffer("attack",System.getProperty("user.dir") + StartScreen.fileThing+"util"+StartScreen.fileThing+"attack.wav", false,1f);
	getSoundPlayer().addToBuffer("death",System.getProperty("user.dir") + StartScreen.fileThing+"util"+StartScreen.fileThing+"death.wav", false,1f);
	getSoundPlayer().addToBuffer("feather",System.getProperty("user.dir") + StartScreen.fileThing+"util"+StartScreen.fileThing+"feather.wav", false,1f);
	getSoundPlayer().addToBuffer("hit",System.getProperty("user.dir") + StartScreen.fileThing+"util"+StartScreen.fileThing+"hit.wav", false,1f);
	getSoundPlayer().addToBuffer("select",System.getProperty("user.dir") + StartScreen.fileThing+"util"+StartScreen.fileThing+"select.wav", false,1f);
	getSoundPlayer().addToBuffer("shot",System.getProperty("user.dir") + StartScreen.fileThing+"util"+StartScreen.fileThing+"shot.wav", false,1f);
	getSoundPlayer().addToBuffer("powerup",System.getProperty("user.dir") + StartScreen.fileThing+"util"+StartScreen.fileThing+"powerup.wav", false,1f);
	}



}
