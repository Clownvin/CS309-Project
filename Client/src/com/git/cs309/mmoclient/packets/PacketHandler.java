package com.git.cs309.mmoclient.packets;

import javax.swing.JOptionPane;

import com.git.cs309.mmoclient.gui.CharactorSelect;
import com.git.cs309.mmoclient.gui.GameGUI;
import com.git.cs309.mmoclient.gui.LoginGUI;
import com.git.cs309.mmoserver.packets.AbstractPacketHandler;
import com.git.cs309.mmoserver.packets.ErrorPacket;
import com.git.cs309.mmoserver.packets.ExtensiveCharacterPacket;
import com.git.cs309.mmoserver.packets.ExtensiveObjectPacket;
import com.git.cs309.mmoserver.packets.MessagePacket;
import com.git.cs309.mmoserver.packets.EventPacket;
import com.git.cs309.mmoserver.packets.NewMapPacket;
import com.git.cs309.mmoserver.packets.Packet;
import com.git.cs309.mmoserver.packets.PacketType;
import com.git.cs309.mmoserver.packets.PlayerCharacterPacket;
import com.git.cs309.mmoserver.packets.SelfPacket;

public final class PacketHandler extends AbstractPacketHandler {
	
	private static final PacketHandler INSTANCE = new PacketHandler();
	
	public static final PacketHandler getInstance() {
		return INSTANCE;
	}
	
	private PacketHandler() {
		
	}

	@Override
	public void handlePacketBlock(Packet packet) {
		packet.getConnection().getPacket(); // to remove packet, since it doesn't do it itself
		switch (packet.getPacketType()) {
		case MESSAGE_PACKET:
			MessagePacket messagePacket = (MessagePacket) packet;
			throw new RuntimeException("Handle messages here.");
			//break; Uncomment this after removing exception.
		case ERROR_PACKET:
			ErrorPacket errorPacket = (ErrorPacket) packet;
			switch (errorPacket.getErrorCode()) {
			case ErrorPacket.GENERAL_ERROR:
				System.out.println("Recieved error from server: "+errorPacket.getErrorMessage());
				break;
			case ErrorPacket.LOGIN_ERROR:
				JOptionPane.showMessageDialog(null, "Error: "+errorPacket.getErrorMessage());
				break;
			default:
				System.err.println("No case for error code: "+errorPacket.getErrorCode());
				System.err.println("Error message: "+errorPacket.getErrorMessage());
				break;
			}
			break;
		case EVENT_PACKET:
			EventPacket eventPacket = (EventPacket) packet;
			switch (eventPacket.getEventCode()) {
			case EventPacket.LOGIN_SUCCESS:
				LoginGUI.getSingleton().setVisible(false);
				CharactorSelect.getSingleton().setVisible(true);
				break;
				
				//throw new RuntimeException("Set your charactor select GUI to visible here.");
				
				//Character select screen.sevisable(true);/
				//	have blacnk
				//over in caharactor select handler eddit charactor select screen
				//then i send packet (interface click packet) for what charactor
				//then i recive on of two packets
				//	new charactor
				//	enter game  (self packet) //set once ever
				//		wait for new map packet and
				//			initalize map
				//			recive 3 types of packets (have them update maps)
				//				spam me with packets
				//				extensive stuff packets (and player charactor)
				//
				//when recive update entity update packet (or extensive)
				
				
				//throw new RuntimeException("Set your Game GUI to visible here.");
				//GameGUI.getSingleton().setVisible(true);
				//break; //YOU'RE gunna need to uncomment this once you remove the exception
			default:
				System.err.println("No case for event code: "+eventPacket.getEventCode());
				break;
			}
			break;
		case ENTITY_UPDATE_PACKET:
			//This packet means that something has happened to an entity that already exists.
			GameGUI.entityUpdate();
			throw new RuntimeException("Handle updating entities here");
		case EXTENSIVE_CHARACTER_PACKET:
			//This packet is telling the client that there is a new character that needs to be managed by the client.
			//calls function in GameGUI to eddit map
			int ExtCharcharacterID = ((ExtensiveCharacterPacket)packet).getCharacterID();
			String ExtCharcharacterName = ((ExtensiveCharacterPacket)packet).getCharacterName();
			int ExtCharhealth = ((ExtensiveCharacterPacket)packet).getHealth();
			int ExtCharmaxHealth = ((ExtensiveCharacterPacket)packet).getMaxHealth();
			int ExtCharuniqueID = ((ExtensiveCharacterPacket)packet).getUniqueID();
			int ExtCharx = ((ExtensiveCharacterPacket)packet).getX();
			int ExtChary = ((ExtensiveCharacterPacket)packet).getY();
			
			GameGUI.addCharactertoMap(ExtCharcharacterID,ExtCharcharacterName,ExtCharhealth,ExtCharmaxHealth,ExtCharuniqueID,ExtCharx,ExtChary);
			break;
		case EXTENSIVE_OBJECT_PACKET:
			int ExtObjstaticID=((ExtensiveObjectPacket)packet).getStaticID();
			int ExtObjuniqueID=((ExtensiveObjectPacket)packet).getUniqueID();
			int ExtObjx=((ExtensiveObjectPacket)packet).getX();
			int ExtObjy=((ExtensiveObjectPacket)packet).getY();
			//TODO
			//this bellow does not exist in packet but name is in packet
			String ExtObjname=((ExtensiveObjectPacket)packet).getObjectName();
			GameGUI.addObjecttoMap(ExtObjstaticID, ExtObjuniqueID, ExtObjx, ExtObjy, ExtObjname);
			//calls function in GameGUI to eddit map
			break;
		case PLAYER_CHARACTER_PACKET:
			int PCBoots = ((PlayerCharacterPacket)packet).getBoots();
			int PCCape = ((PlayerCharacterPacket)packet).getCape();
			int PCChestPiece = ((PlayerCharacterPacket)packet).getChestPiece();
			byte PCGender = ((PlayerCharacterPacket)packet).getGender();
			int PCGloves = ((PlayerCharacterPacket)packet).getGloves();
			int PCHeadPiece = ((PlayerCharacterPacket)packet). getHeadPiece();
			int PCHealth = ((PlayerCharacterPacket)packet).getHealth();
			int PCLeftHand = ((PlayerCharacterPacket)packet).getLeftHand();
			int PCLeggings = ((PlayerCharacterPacket)packet).getLeggings();
			int PCLevel = ((PlayerCharacterPacket)packet).getLevel();
			int PCMaxHealth= ((PlayerCharacterPacket)packet).getMaxHealth();
			String PCName= ((PlayerCharacterPacket)packet).getName();
			int PCRightHand= ((PlayerCharacterPacket)packet).getRightHand();
			int PCUniqueID= ((PlayerCharacterPacket)packet).getUniqueID();
			int PCX= ((PlayerCharacterPacket)packet).getX();
			int PCY= ((PlayerCharacterPacket)packet).getY();
			
			GameGUI.addPlayertoMap(PCBoots, PCCape, PCChestPiece, PCGender,PCGloves, PCGloves, PCHeadPiece, PCHealth, PCLeftHand, PCLeggings, PCLevel, PCMaxHealth, PCName, PCRightHand, PCUniqueID, PCX, PCY); 
			//calls function in GameGUI to eddit map
			throw new RuntimeException("Handle new players here.");
		case TEST_PACKET:
			System.out.println("No code for test packet");
			break;
		case ADMIN_COMMAND_PACKET:
			break;
		case CHARACTER_STATUS_PACKET:
			break;
		case ENTITY_CLICK_PACKET:
			break;
		case INTERFACE_CLICK_PACKET:
			break;
		case ITEM_CONTAINER_PACKET:
			break;
		case LOGIN_PACKET:
			//from server to client
			break;
		case MOVE_PACKET:
			break;
		case NEW_MAP_PACKET:
			String mapName= ((NewMapPacket)packet).getMapName();
			GameGUI.initalizeMap(mapName);
			//map_name
			//load map
			break;
		case NULL_PACKET:
			//something f'ed up
			break;
		case PLAYER_EQUIPMENT_PACKET:
			break;
		case SELF_PACKET:
			//send the  client ID, 
			//i am using this area to transition from 
			//	(new charactor or charactor select) to GameGUI
			int ID = ((SelfPacket)packet).getUniqueID();
			CharactorSelect.getSingleton().setVisible(false);
			//make new charactor .getSingleton().setVisible(false);
			GameGUI.setID(ID);
			GameGUI.getSingleton().setVisible(true);
			break;
		case SERVER_MODULE_STATUS_PACKET:
			break;
		case USER_STATUS_PACKET:
			break;
		//case for reciving charactors to select
		default:
			System.err.println("No case for packet type: "+packet.getPacketType());
			break;
		}
	}
}
